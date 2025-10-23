from collections import deque
from typing import Optional, List, Dict


"""
# Definition for a Node.
class Node:
    def init(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

class Solution:
    """
    Clones an undirected graph. Each node in the graph contains a label and a list of its neighbors.
    The graph is represented by a given node, and the function should return a reference 
    to the cloned graph's starting node.
    """
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        """
        Clones a graph using Breadth-First Search (BFS).

        The algorithm maintains a map to store the relationship between the original 
        nodes and their corresponding cloned nodes to prevent infinite loops (due 
        to cycles) and redundant cloning.

        Time Complexity: O(V + E), where V is the number of nodes (vertices) and 
                         E is the number of edges. Each node and edge is processed once.
        Space Complexity: O(V), used by the hash map (cloned_map) and the queue.

        :param node: The starting node of the graph to be cloned.
        :return: The starting node of the deep copy (cloned graph).
        """
        if not node:
            # Handle the empty graph case.
            return None

        # 1. Initialize the map (Original Node -> Cloned Node) and the queue for BFS.
        # This map ensures we don't clone the same node multiple times and 
        # helps look up the cloned version of a neighbor.
        cloned_map: Dict[Node, Node] = {}
        queue: deque[Node] = deque()

        # 2. Clone the starting node and add it to the map and queue.
        new_node = Node(node.val)
        cloned_map[node] = new_node
        queue.append(node)
        
        # 3. Perform BFS traversal.
        while queue:
            original_node = queue.popleft()
            cloned_node = cloned_map[original_node]
            
            # 4. Iterate through all neighbors of the current original node.
            for neighbor in original_node.neighbors:
                # Check if the neighbor has already been cloned.
                if neighbor not in cloned_map:
                    # If not cloned, create a new clone, map it, and add the original 
                    # neighbor to the queue for later processing.
                    new_neighbor = Node(neighbor.val)
                    cloned_map[neighbor] = new_neighbor
                    queue.append(neighbor)
                
                # Get the cloned neighbor from the map (either newly created or existing).
                cloned_neighbor = cloned_map[neighbor]
                
                # 5. Connect the cloned_node to its cloned_neighbor in the new graph.
                cloned_node.neighbors.append(cloned_neighbor)
        
        # 6. Return the cloned version of the starting node.
        return cloned_map[node]