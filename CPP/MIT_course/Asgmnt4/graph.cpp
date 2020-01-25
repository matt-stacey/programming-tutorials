/*
 * graph.cpp
 *
 *  Created on: Jun 23, 2019
 *      Author: matth
 */

#include "graph.h"
#include <vector>
#include <stdexcept>

Graph::Graph(const std::vector<int> &starts, const std::vector<int> &ends) {
	if (starts.size() != ends.size()) {
		throw std::invalid_argument("vector length mismatch!");
	}

	for(int i = 0; i < starts.size; i++) {
		int start = starts[i], end = ends[i];
		outgoing[start].push_back(end);
		outgoing[end];
	}
}
int Graph::numOutgoing(const int nodeID) const {
	return adjacent(nodeID).size();
}
const std::vector<int> &Graph::adjacent(const int nodeID) const {
	std::map::map<int, std::vector<int> >::const_iterator it = outgoing.find(nodeID);

	if(it == outgoing.end()) {
		throw std::invalid_argument("nodeID not found!");
	}

	return outgoing.at(nodeID); // return it->second; // in solution
}
