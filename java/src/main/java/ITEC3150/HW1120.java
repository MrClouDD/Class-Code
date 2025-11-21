package ITEC3150;

/**
 * Merging Nodes in a graph:
 * 1) Input and output graphs are given and you must figure out the merge steps
 * in between
 * 2) Lower merges into highest and only nodes with the same parent can be
 * merges
 * 
 * Logic:
 * Repeat until no merge:
 * 1) Start with all non-included leaves. Merge into highet parent
 * 2) Check parent's of the leaves of the final graph. If they have final and
 * init have different parents, merges lowest parent into highest as long as
 * they connect (DFS)
 */
public class HW1120 {

}
