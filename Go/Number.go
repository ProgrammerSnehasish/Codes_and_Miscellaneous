package main

func occurence (arr [15]int) map[int]int {
	occuring := make(map[int]int)
	for i := 0; i < len(arr) ; i++ {
		occuring[arr[i]]++
	}
	return occuring
}