package fr.modele.message;

public class Message {
	
	private String texte;
	private EMessageType typeMessage;
	
	public Message(String texte, EMessageType typeMessage) {
		super();
		this.texte = texte;
		this.typeMessage = typeMessage;
	}

	public String getTexte() {
		return texte;
	}

	public EMessageType getTypeMessage() {
		return typeMessage;
	}

}
