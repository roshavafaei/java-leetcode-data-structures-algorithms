class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);

            else
                map.put(num, 1);
        }

        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort((a, b) -> map.get(b) - map.get(a));

        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {
            answer[i] = keys.get(i);
        }
        return answer;
    }
}
