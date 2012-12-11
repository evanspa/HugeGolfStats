<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="signup.form.address.streetline1" />:
			</td>
			<td align="left">
				<html:text property="streetline1" size="45" />
			</td>
		</tr>	
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="signup.form.address.streetline2" />:
			</td>
			<td align="left">
				<html:text property="streetline2" size="45" />
			</td>
		</tr>
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="signup.form.address.streetline3" />:
			</td>
			<td align="left">
				<html:text property="streetline3" size="45" />
			</td>
		</tr>
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="signup.form.address.city" />:
			</td>
			<td align="left">
				<html:text property="city" size="45" />
			</td>
		</tr>
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="signup.form.address.country" />:
			</td>
			<td align="left">
				<!-- <html:text property="country" size="45" /> -->
				<html:select property="countryId">
					<html:options
						name="<%= AttributeKeyConstants.UTIL %>" 					
						property="countries.id" />						
						labelName="<%= AttributeKeyConstants.UTIL %>" 
						labelProperty="countries.name"
				</html:select>
			</td>
		</tr>
		<tr>
			<td align="right" style="font-weight: bold">
				<bean:message key="signup.form.address.stateprovince" />:
			</td>
			<td align="left">
				<html:text property="stateProvinceId" size="45" />
			</td>
		</tr>	