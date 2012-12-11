<jsp:useBean
	id="addressBean"
	class="name.paulevans.golf.hibernate.Address"
	scope="request" />

			<tr><td colspan="2"><hr /></td></tr>
				<tr>
					<td align="right">
						<strong>
							<bean:message 
								key="signup.form.address.streetline1" />
						</strong>:
					</td>
					<td align="left">
						<bean:write name="addressBean" property="streetline1" />
					</td>
				</tr>	
				<tr>
					<td align="right">
						<strong>
							<bean:message 
								key="signup.form.address.streetline2" />
						</strong>:
					</td>
					<td align="left">
						<bean:write name="addressBean" property="streetline2" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong>
							<bean:message 
								key="signup.form.address.streetline3" />
						</strong>:
					</td>
					<td align="left">
						<bean:write name="addressBean" property="streetline3" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong>
							<bean:message key="signup.form.address.city" />
						</strong>:
					</td>
					<td align="left">
						<bean:write name="addressBean" property="city" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong>
							<bean:message 
								key="signup.form.address.stateprovince" />
						</strong>:
					</td>
					<td align="left">
						<bean:write name="addressBean" 
							property="stateProvince" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong>
							<bean:message key="signup.form.address.country" />
						</strong>:
					</td>
					<td align="left">
						<bean:write name="addressBean" property="country" />
					</td>
				</tr>