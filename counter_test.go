package winden

import (
	"math/rand"
	"testing"
)

const length = 1000000

var sorted = make([]int, length)
var shuffled = rand.Perm(length)

func init() {
	for i := 0; i < length; i++ {
		sorted[i] = i
	}
}

func BenchmarkSortedArray(b *testing.B) {
	for i := 0; i < b.N; i++ {
		sum := 0
		for _, v := range sorted {
			if v < length/2 {
				sum++
			}
		}
	}
}

func BenchmarkShuffledArray(b *testing.B) {
	for i := 0; i < b.N; i++ {
		sum := 0
		for _, v := range shuffled {
			if v < length/2 {
				sum++
			}
		}
	}
}
