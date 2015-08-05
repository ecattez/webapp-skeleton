-- authentication(authentication_id, username, password_salt, password_hash);
-- securid (user_id, token_id, expiration_date);
-- user (user_id, first_name, last_name, email, phone, authentication_id);
-- company (company_id, name, address, additionnal_address, zip_code);
-- group (group_id, group_name);
-- permission (permission_id, group_id);

-- membership (membership_id, user_id, company_id, group_id);


-- Un utilisateur appartient à une seule company et un seul groupe
---- donc dans membership:
--> [user_id] est unique
--> [user_id, company_id, group_id] est un trio unique
--> [user_id, company_id] est un duo unique
--> [user_id, group_id] est un duo unique

-- Utilisation du UUID et override de ::equals et ::hashCode par UUID::equals et UUID::hashCode

---- class: Membership.class
---- enum: MemberType.{USER,COMPANY,GROUP} -> getId() { "user_id", "group_id", "company_id" }
---- class: Member.class	getMemberType() -> return MemberType

---- Exemple User.class extends Member
-- getUUID(), getFirstName(), getLastName(), getEmail(), getPhone()

---- Exemple MemberDao.class
-- getMembershipBy(Member member) -> eq(getMemberId(), getUUID())