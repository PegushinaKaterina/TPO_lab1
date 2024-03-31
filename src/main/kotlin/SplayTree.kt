class SplayTree {
    class Node(var key: Int, var left: Node? = null, var right: Node? = null)

    private fun rightRotate(p: Node?): Node? {
        val x = p?.left
        p?.left = x?.right
        x?.right = p
        return x
    }

    private fun leftRotate(p: Node?): Node? {
        val x = p?.right
        p?.right = x?.left
        x?.left = p
        return x
    }

    fun splay(root: Node?, key: Int): Node? {
        if (root == null || root.key == key) return root
        if (root.key > key) {
            if (root.left == null) return root

            if (root.left?.key!! < key) {
                root.left?.right = splay(root.left?.right, key)
                if (root.left?.right != null) root.left = leftRotate(root.left)
            }

            return if (root.left == null) root else rightRotate(root)
        } else {
            if (root.right == null) return root

            if (root.right?.key!! > key) {
                root.right?.left = splay(root.right?.left, key)
                if (root.right?.left != null) root.right = rightRotate(root.right)
            }

            return if (root.right == null) root else leftRotate(root)
        }
    }

    fun insert(root: Node?, key: Int): Node {
        if (root == null) return Node(key)
        var rootLocal = splay(root, key)

        return when {
            key < rootLocal?.key!! -> {
                val newNode = Node(key)
                newNode.right = rootLocal
                newNode.left = rootLocal.left
                rootLocal.left = null
                newNode
            }

            else -> {
                val newNode = Node(key)
                newNode.left = rootLocal
                newNode.right = rootLocal.right
                rootLocal.right = null
                newNode
            }
        }
    }

    fun search(root: Node?, key: Int): Node? {
        if (containsElem(root, key)) {
            return splay(root, key)
        }
        return null
    }

    fun delete(root: Node?, key: Int): Node? {
        var temp = splay(root, key) ?: return null
        if (temp.key != key) return temp

        val leftSubtree = temp.left
        val rightSubtree = temp.right

        temp.left = null
        temp.right = null

        val newRoot = splay(leftSubtree, Int.MAX_VALUE)
        newRoot?.right = rightSubtree

        return newRoot
    }


    private fun containsElem(root: Node?, key: Int): Boolean {
        if (root == null) {
            return false
        } else if (root.key == key) {
            return true
        } else if (root.key > key) {
            return containsElem(root.left, key)
        } else {
            return containsElem(root.right, key)
        }
    }
}