class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < stones.length; i++) {
            maxHeap.add(stones[i]);
        }

        while (maxHeap.size() > 1) {
            int firstLargestStone = maxHeap.poll();
            int secondLargestStone = maxHeap.poll();

            if (firstLargestStone == secondLargestStone)
                continue;

            maxHeap.add(firstLargestStone - secondLargestStone);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}
