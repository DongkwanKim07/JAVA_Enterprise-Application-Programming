/***************************************************************************
 * File:  MembershipCard.java Course materials (22F) CST 8277
 * 
 * @author Teddy Yap
 * @author Shariar (Shawn) Emami
 * @date August 28, 2022
 * 
 */
package acmecollege.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("unused")

/**
 * The persistent class for the membership_card database table.
 */
//TODO MC01 - Add the missing annotations.
@Entity
@Table(name = "membership_card")
@NamedQuery(name = "MembershipCard.findAll", query = "SELECT a FROM MembershipCard a")
@AttributeOverride(name = "id", column = @Column(name = "card_id"))
//TODO MC02 - Do we need a mapped super class?  If so, which one?
public class MembershipCard extends PojoBase implements Serializable {
	private static final long serialVersionUID = 1L;

	// TODO MC03 - Add annotations for 1:1 mapping.  Changes here should cascade.
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "membership_id", referencedColumnName = "membership_id")
	private ClubMembership clubMembership;

	// TODO MC04 - Add annotations for M:1 mapping.  Changes here should not cascade.
	@OneToOne
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student owner;

	// TODO MC05 - Add annotations.
	@Basic(optional = false)
	@Column(name = "signed")
	private byte signed;

	public MembershipCard() {
		super();
	}
	
	public MembershipCard(ClubMembership clubMembership, Student owner, byte signed) {
		this();
		this.clubMembership = clubMembership;
		this.owner = owner;
		this.signed = signed;
	}

	public ClubMembership getClubMembership() {
		return clubMembership;
	}

	public void setClubMembership(ClubMembership clubMembership) {
		this.clubMembership = clubMembership;
		//We must manually set the 'other' side of the relationship (JPA does not 'do' auto-management of relationships)
		if (clubMembership != null) {
			clubMembership.setCard(this);
		}
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
		//We must manually set the 'other' side of the relationship (JPA does not 'do' auto-management of relationships)
		if (owner != null) {
			owner.getMembershipCards().add(this);
		}
	}

	public byte getSigned() {
		return signed;
	}

	public void setSigned(byte signed) {
		this.signed = signed;
	}

	public void setSigned(boolean signed) {
		this.signed = (byte) (signed ? 0b0001 : 0b0000);
	}
	
	//Inherited hashCode/equals is sufficient for this entity class

}