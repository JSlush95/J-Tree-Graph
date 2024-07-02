package org.example;

import java.util.Stack;

public class Path {
    Stack<String> localStack = new Stack<String>();

    public String add(String input){ // The input is of the format: [ n_1, ... , n_k ] a sequence of vertices to form a path.
        if (input.equals(null)) {
            return null;
        }
        return this.localStack.push(input);
    }

    public String remove(){     // Wrapper class for removal for the stack class's pop() method.
        return this.localStack.pop();
    }

    public String last(){   // Wrapper class for the stack class's peek() method.
        return this.localStack.peek();
    }

    public int size() {
        if(localStack.isEmpty()){
            return 0;
        }
        return this.localStack.size();
    }

    public void emptyStack(){
        this.localStack.clear();
    }

    public String toString(){
        if(this.localStack.isEmpty()){
            return "No path found from source to destination.";
        }

        String retStr = "";
        String[] tempArray = new String[this.size()];       // Need to declare an array to convert the toArray's object to an ArrayList for index traversal for formatting.
        tempArray = this.localStack.toArray(tempArray);

        for(int i = 0; i < tempArray.length; i++){
            retStr = retStr + tempArray[i] + " > ";
        }
        retStr = retStr.substring(0, retStr.length()-3);  // Reformatting to remove the trailing >
        return retStr;
    }
}
