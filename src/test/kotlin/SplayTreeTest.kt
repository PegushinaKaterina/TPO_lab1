import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SplayTreeTest {

    private val splayTree = SplayTree()

    @Test
    fun testEmpty() {
        val emptyNode: SplayTree.Node? = null

        assertNull(splayTree.search(emptyNode, 0))

        assertTrue(compareTree(splayTree.insert(emptyNode, 1), SplayTree.Node(1)))
    }

    @Test
    fun testSingle() {
        val singleNode = SplayTree.Node(1)

        assertNull(splayTree.search(singleNode, 0))
        assertTrue(compareTree(splayTree.search(singleNode, 1), singleNode))

        assertTrue(compareTree(splayTree.insert(singleNode, 1), SplayTree.Node(1, singleNode)))
        assertTrue(compareTree(splayTree.insert(singleNode, 2), SplayTree.Node(2, singleNode)))
        assertTrue(compareTree(splayTree.insert(singleNode, 0), SplayTree.Node(0, null, singleNode)))
    }

    @Test
    fun testMultiSearchNonExist() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)

        val root5 = SplayTree.Node(5, node3, node7)

        assertNull(splayTree.search(root5, 0))
    }


    @Test
    fun testMultiSearchRoot() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)
        val root5 = SplayTree.Node(5, node3, node7)

        assertTrue(compareTree(splayTree.search(root5, 5), root5))
    }

    @Test
    fun testMultiSearchLeft() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)
        val root5 = SplayTree.Node(5, node3, node7)

        val root3 = SplayTree.Node(3, null, SplayTree.Node(5, null, SplayTree.Node(7)))

        assertTrue(compareTree(splayTree.search(root5, 3), root3))
    }

    @Test
    fun testMultiSearchRight() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)

        val root5 = SplayTree.Node(5, node3, node7)
        val root7 = SplayTree.Node(7, SplayTree.Node(5, SplayTree.Node(3)))

        assertTrue(compareTree(splayTree.search(root5, 7), root7))
    }

    @Test
    fun testMultiInsertLeftOfRoot() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)

        val root5 = SplayTree.Node(5, node3, node7)

        val newRoot = SplayTree.Node(4, SplayTree.Node(3), SplayTree.Node(5, null, SplayTree.Node(7)))

        assertTrue(compareTree(splayTree.insert(root5, 4), newRoot))
    }


    @Test
    fun testMultiInsertRightOfRoot() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)

        val root5 = SplayTree.Node(5, node3, node7)

        val newRoot = SplayTree.Node(6, SplayTree.Node(5, SplayTree.Node(3)), SplayTree.Node(7))

        assertTrue(compareTree(splayTree.insert(root5, 6), newRoot))
    }
    @Test
    fun testMultiInsertLeftOfRoot2() {
        val root5 = SplayTree.Node(6, SplayTree.Node(3, SplayTree.Node(2), SplayTree.Node(4)),  SplayTree.Node(7))

        val newRoot = SplayTree.Node(5, SplayTree.Node(4, SplayTree.Node(3,SplayTree.Node(2)), null), SplayTree.Node(6, null, SplayTree.Node(7)))

        assertTrue(compareTree(splayTree.insert(root5, 5), newRoot))
    }

    @Test
    fun testMultiInsertRightOfRoot2() {
        val root5 = SplayTree.Node(4, SplayTree.Node(3), SplayTree.Node(7,  SplayTree.Node(6), SplayTree.Node(8)))

        val newRoot = SplayTree.Node(5, SplayTree.Node(4, SplayTree.Node(3)), SplayTree.Node(6, null, SplayTree.Node(7, null, SplayTree.Node(8))))


        assertTrue(compareTree(splayTree.insert(root5, 5), newRoot))
    }


    @Test
    fun testDeleteNonExist() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)

        val root5 = SplayTree.Node(5, node3, node7)

        val newRoot =SplayTree.Node(3, null, SplayTree.Node(5, null, node7))

        assertTrue(compareTree(splayTree.delete(root5, 4), newRoot))
    }

    @Test
    fun testDeleteExist() {
        val node3 = SplayTree.Node(3)
        val node7 = SplayTree.Node(7)

        val root5 = SplayTree.Node(5, node3, node7)

        val newRoot =SplayTree.Node(3, null, SplayTree.Node(7))

        assertTrue(compareTree(splayTree.delete(root5, 5), newRoot))
    }




    private fun compareTree(root1: SplayTree.Node?, root2: SplayTree.Node?): Boolean {
        if (root1 == null && root2 == null) {
            return true
        } else if (root1 != null && root2 != null) {
            if (root1.key == root2.key) {
                return compareTree(root1.left, root2.left) && compareTree(root1.right, root2.right)
            }
        }
        return false
    }
}