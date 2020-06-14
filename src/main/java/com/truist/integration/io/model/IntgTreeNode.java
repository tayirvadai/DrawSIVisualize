package com.truist.integration.io.model;

import java.util.ArrayList;
import java.util.List;

public class IntgTreeNode<T> {
    private List<IntgTreeNode<T>> children = new ArrayList<IntgTreeNode<T>>();
    private IntgTreeNode<T> parent = null;
    private T data = null;

    public IntgTreeNode(T data) {
        this.data = data;
    }

    public IntgTreeNode(T data, IntgTreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<IntgTreeNode<T>> getChildren() {
        return children;
    }

    public void setParent(IntgTreeNode<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        IntgTreeNode<T> child = new IntgTreeNode<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(IntgTreeNode<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }
}