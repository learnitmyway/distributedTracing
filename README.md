# Distributed Tracing

## Input

Update `app/src/main/resources/input.txt` to change the input

## Useful commands

* Run all tests with `./gradlew test -i`
* Run pre-commit check with `./gradlew check`
* Run app with `./gradlew run`

## Assumptions

- Input is one line (This implies only one line needs to be parsed)
- Microservices can only be labelled as an uppercase letter in the English alphabet.
  (This implies only one uppercase letter needs to be parsed)
- Graph is connected (This implies there are no disconnected nodes)
- all nodes exist when doing a calculation (This implies there is no need to handle missing nodes)
- shortest path exists (This implies there is no need to handle non-existent paths)
- shortest path cannot be 0 (This implies the shortest distance can be reset if it was previously 0)

## Design

- A `Node` consists of an id and a list of adjacent edges. An `Edge` consists of a source `Node`, a destination `Node`
  and a weight. This means the whole graph can be determined from one node
- The code is more functional than object orientated as I felt it was a better fit for this exercise.
- The graph is represented as a Map with a Node ID as the key (eg. A) and the `Node` as the value.
- Calculating the trace count with hops or latency is a variation of breadth-first search
- Calculating the shortest trace is done with Dijkstra's algorithm with a slight variation to account for cycles. In
  this case it assumed that the shortest path cannot be 0

## Potential improvements

- It should be possible to get all paths with Breadth or Depth-First Search based on a condition (eg. max hops). I
  wonder if it would have been possible to reuse one function and pass it a callback with a condition (eg. hops < max
  hops)
- passing the input file name as a command-line argument
- parsing different questions
- A priority queue for Dijkstra's algorithm
