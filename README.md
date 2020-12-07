# Distributed Tracing

## Input

Update `app/src/main/resources/input.txt` to change the input

## Useful commands

* Run all tests with `./gradlew test -i`
* Run pre-commit check with `./gradlew check`
* Run app with `./gradlew run`

## Assumptions

- Input is one line (This implies only one line needs to be parsed)
- Microservices can only be labelled as an uppercase letter in the English alphabet. (This implies there can't be more
  than 26 microservices.)
- Graph is connected (This implies there are no disconnected nodes)
- all nodes exist when doing an calculation (This implies there is no need to handle missing nodes)
- shortest path exists (This implies there is no need to handle non-existent paths)
- shortest path cannot be 0 (This implies that 0 can be replaced)

## Design

- The graph is represented as a map of nodes where the node ID is the key and the node is the value
- The only methods that modify state are `Node` and `Edge`. All other methods have no side effects. This led to a lot of
  utility classes. I'm not sure if this is against convention in Java
- Calculating the trace count with hops or latency is a variation of breadth-first search
- Calculating the shortest trace is done with Dijkstra's algorithm with a slight variation to account for cycles. In
  this case it assumed that the shortest path cannot be 0

