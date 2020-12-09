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
  and a weight`. This means the whole graph can be determined from one node
- The code is more functional than object orientated as I felt it was a better fit for this exercise. I could have used
  a set instead of a map but then I would have had to interate over the nodes each time. Might have been nicer to read,
  though.
- Calculating the trace count with hops or latency is a variation of breadth-first search
- Calculating the shortest trace is done with Dijkstra's algorithm with a slight variation to account for cycles. In
  this case it assumed that the shortest path cannot be 0

## Potential improvements

- passing the input file as a command-line argument
- parsing the questions
- A priority queue for Dijkstra's algorithm
