/*
 * graph.h
 *
 *  Created on: Jun 23, 2019
 *      Author: matth
 */

#include <vector>
#include <map>

#ifndef GRAPH_H_
#define GRAPH_H_

class Graph {
protected:
	std::map::map<int, std::vector<int> > outgoing; // maps ints to their vectors

public:
	Graph(const std::vector<int> &starts, const std::vector<int> &ends);
	int numOutgoing(const int nodeID) const;
	const std::vector<int> &adjacent(const int nodeID) const;
};



#endif /* GRAPH_H_ */
