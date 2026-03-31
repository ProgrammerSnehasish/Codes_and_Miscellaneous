package main

import "fmt"

type node struct {
	value   int
	address *node
}

type linkedlist struct {
	head *node
}

func createList() *linkedlist {
	list := new(linkedlist)
	list.head = nil
	return list
}

func (l *linkedlist) append(value int) {
	p := new(node)
	p.value = value
	p.address = nil

	if l.head == nil {
		l.head = p
	} else {
		q := l.head
		for q.address != nil {
			q = q.address
		}
		q.address = p
	}
}

func (l *linkedlist) display() {
	q := l.head
	for q != nil {
		fmt.Print(q.value, " ")
		q = q.address
	}
}