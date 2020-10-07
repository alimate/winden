package winden

type Counter struct {
	numbers []int
}

func (c *Counter) Count() int {
	count := 0
	for _, v := range c.numbers {
		if v < len(c.numbers)/2 {
			count++
		}
	}

	return count
}
