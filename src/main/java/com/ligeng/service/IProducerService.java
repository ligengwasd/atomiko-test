package com.ligeng.service;

import javax.jms.Destination;
import java.io.Serializable;

public interface IProducerService {

	/**
	 * ������ͨ�Ĵ��ı���Ϣ
	 * @param destination
	 * @param message
	 */
	public void sendMessage(Destination destination, String message);
	
	/**
	 * ����һ��ObjectMessage
	 * @param destination
	 * @param obj
	 */
	public void sendMessage(Destination destination, Serializable obj);
	
}
