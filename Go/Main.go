package main

import "fmt"

func main() {
	list := createList()

	list.append(10)
	list.append(20)
	list.append(30)
	list.append(40)

	fmt.Println("Linked List elements:")
	list.display()
}