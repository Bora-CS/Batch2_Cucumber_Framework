package Batch2_Cucumber_Framework;

import java.util.Locale;
import java.util.Map;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

public class Configurer implements TypeRegistryConfigurer {

	@Override
	public void configureTypeRegistry(TypeRegistry registry) {

		registry.defineDataTableType(new DataTableType(UserInfo.class, new TableEntryTransformer<UserInfo>() {
			@Override
			public UserInfo transform(Map<String, String> entry) throws Throwable {
				return new UserInfo(entry.get("ID"), entry.get("Username"), entry.get("Password"));
			}
		}));
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

}
