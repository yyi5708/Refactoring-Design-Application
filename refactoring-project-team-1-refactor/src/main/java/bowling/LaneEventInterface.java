package bowling;
import java.util.HashMap;

public interface LaneEventInterface extends java.rmi.Remote {
	public int getFrameNum( ) throws java.rmi.RemoteException;
	public HashMap getScore( ) throws java.rmi.RemoteException;
	public int[] getCurScores( ) throws java.rmi.RemoteException;
	public int getIndex() throws java.rmi.RemoteException;
	public int getFrame() throws java.rmi.RemoteException;
	public int getBall() throws java.rmi.RemoteException;
	public int[][] getCumulScore() throws java.rmi.RemoteException;
	public Party getParty() throws java.rmi.RemoteException;
	public Bowler getBowler() throws java.rmi.RemoteException;

}

