package main

func factorial (number int) int {
	var factorial_val int = 1
	for i := 2; i <= number; i++ {
		factorial_val = factorial_val * i
	}

	return factorial_val
}