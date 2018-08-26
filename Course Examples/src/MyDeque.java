// CS 401 Fall 2017
// MyDeque class to implement a simple deque
// Complete the methods indicated.  Be careful about the
// special cases indicated.  See lab for details on how
// to implement the methods.

public class MyDeque implements SimpleDeque
{
	Object [] theData;
	int numItems;

	public MyDeque(int maxItems)
	{
		theData = new Object[maxItems];
		numItems = 0;
	}
	
	public boolean isEmpty()
	{
		return (numItems == 0);
	}
	
	public void addFront(Object X)
	{
		if(numItems < theData.length){
			for(int i = numItems - 1; i >= 0; i--)
				theData[i + 1] = theData[i];
			theData[0] = X;
			numItems++;
		}
	}

	public void addRear(Object X)
	{
		if(numItems < theData.length)
			theData[numItems] = X;
		numItems++;
	}

	public Object removeFront()
	{
		if(numItems == 0)
			return null;
		else{
			Object o = theData[0];
			for(int i = 0; i < numItems; i++)
				theData[i] = theData[i + 1];
			numItems--;
			return o;
		}
	}

	public Object removeRear()
	{
		if(numItems == 0)
			return null;
		else{
			Object o = theData[numItems - 1];
			theData[numItems - 1] = null;
			numItems--;
			return o;
		}
	}
}