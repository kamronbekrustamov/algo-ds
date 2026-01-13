/**
 * Solution to calculate the total area covered by two rectilinear rectangles.
 * The total area is the sum of both areas minus their intersection area.
 */
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 1. Calculate the area of the first rectangle
        int areaA = (ax2 - ax1) * (ay2 - ay1);
        
        // 2. Calculate the area of the second rectangle
        int areaB = (bx2 - bx1) * (by2 - by1);
        
        // 3. Determine the boundaries of the intersection rectangle
        // The overlap starts at the maximum of the left/bottom edges
        // and ends at the minimum of the right/top edges.
        int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        
        int intersectionArea = 0;
        
        // 4. If both width and height are positive, an intersection exists
        if (overlapWidth > 0 && overlapHeight > 0) {
            intersectionArea = overlapWidth * overlapHeight;
        }
        
        // 5. Total Area = Area A + Area B - Overlap
        return areaA + areaB - intersectionArea;
    }
}