package winden

import (
	"math/rand"
	"testing"
)

const length = 10000

var sorted = &Counter{numbers: sortedSlice(length)}
var shuffled = &Counter{numbers: rand.Perm(length)}

func BenchmarkSortedArray(b *testing.B) {
	for i := 0; i < b.N; i++ {
		sorted.Count()
	}
}

func BenchmarkShuffledArray(b *testing.B) {
	for i := 0; i < b.N; i++ {
		shuffled.Count()
	}
}

func sortedSlice(length int) []int {
	v := make([]int, length)
	for i := 0; i < length; i++ {
		v[i] = i
	}

	return v
}
