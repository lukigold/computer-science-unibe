public class Queue{
  private QueueNode list;

  public Queue(){ list = null;}

  public void enqueue(Object obj)
  {
    QueueNode node = new QueueNode(obj);
    QueueNode current;

    if(list==null)
    {
      list = node;
    }
    else
    {
      current = list;
      while(current.next != null)
      {
        current = current.next;
      }
      current.next = node;
    }
  }

  public Object dequeue() throws EmptyQueueException
  {
    EmptyQueueException EmptyQueueException = new EmptyQueueException("Empty Queue.");

    if(this.isEmpty())
    {
      throw EmptyQueueException;
    }

    QueueNode first = list;

    if(list != null && list.hasNext())
    {
      first = list;
      list = list.next;
    }
    else if(list != null)
    {
      first = list;
      list = null;
    }
    else
    {
      throw EmptyQueueException;
    }

    return first.object;
  }
  public boolean isEmpty()
  {
    if(list == null)
    {
      return true;
    }
    return false;
  }

  private class QueueNode
  {
    public Object object;
    public QueueNode next;

    public QueueNode(Object obj)
    {
      object = obj;
      next = null;
    }
    public boolean hasNext()
    {
      return (next != null);
    }
  }
}

class EmptyQueueException extends Exception
{
  EmptyQueueException(String message)
  {
    super(message);
  }
}
