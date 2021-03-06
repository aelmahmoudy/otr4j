/*
 * otr4j, the open source java otr library.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.otr4j;

import java.security.KeyPair;

import net.java.otr4j.session.InstanceTag;
import net.java.otr4j.session.SessionID;

/**
 * 
 * This interface should be implemented by the host application. It is required
 * for otr4j to work properly.
 * 
 * @author George Politis
 * 
 */
public abstract interface OtrEngineHost {
	public abstract void injectMessage(SessionID sessionID, String msg)
			throws OtrException;

	public abstract void unreadableMessageReceived(SessionID sessionID)
			throws OtrException;

	public abstract void unencryptedMessageReceived(SessionID sessionID,
			String msg) throws OtrException;

	public abstract void showError(SessionID sessionID, String error)
			throws OtrException;

	public abstract void smpError(SessionID sessionID, int tlvType,
			boolean cheated) throws OtrException;

	public abstract void smpAborted(SessionID sessionID) throws OtrException;

	public abstract void finishedSessionMessage(SessionID sessionID,
			String msgText) throws OtrException;

	public abstract void requireEncryptedMessage(SessionID sessionID,
			String msgText) throws OtrException;

	public abstract OtrPolicy getSessionPolicy(SessionID sessionID);

	/**
	 * Get instructions for the necessary fragmentation operations.
	 *
	 * If no fragmentation is necessary, return {@link Integer#MAX_VALUE} to
	 * indicate the largest possible fragment size. Return any positive
	 * integer to specify a maximum fragment size and enable fragmentation
	 * using that boundary condition. If specified max fragment size is too
	 * small to fit at least the fragmentation overhead + some part of the
	 * message, fragmentation will fail with an IOException when
	 * fragmentation is attempted during message encryption.
	 *
	 * @param sessionID
	 *            the session ID of the session
	 * @return Returns the maximum fragment size allowed. Or return the
	 * maximum value possible, {@link Integer#MAX_VALUE}, if fragmentation
	 * is not necessary.
	 */
	public abstract int getMaxFragmentSize(SessionID sessionID);

	public abstract KeyPair getLocalKeyPair(SessionID sessionID)
			throws OtrException;

	public abstract byte[] getLocalFingerprintRaw(SessionID sessionID);

	public abstract void askForSecret(SessionID sessionID, InstanceTag receiverTag, String question);

	public abstract void verify(SessionID sessionID, String fingerprint, boolean approved);

	public abstract void unverify(SessionID sessionID, String fingerprint);

	public abstract String getReplyForUnreadableMessage(SessionID sessionID);

	public abstract String getFallbackMessage(SessionID sessionID);

	public abstract void messageFromAnotherInstanceReceived(SessionID sessionID);

	public abstract void multipleInstancesDetected(SessionID sessionID);
}
