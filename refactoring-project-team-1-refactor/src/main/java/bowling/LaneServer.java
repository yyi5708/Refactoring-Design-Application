package bowling;
public interface LaneServer extends java.rmi.Remote {
	public void subscribe(LaneObserver toAdd) throws java.rmi.RemoteException;
};

