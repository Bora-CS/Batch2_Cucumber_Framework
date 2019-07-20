package CucumberDataTransformers;

import java.util.List;
import java.util.Locale;

import DataObjects.UserInfo;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableRowTransformer;

public class Configurer implements TypeRegistryConfigurer {

	@Override
	public void configureTypeRegistry(TypeRegistry registry) {
		registry.defineDataTableType(
			new DataTableType(
					UserInfo.class, 
					new TableRowTransformer<UserInfo>() {
						@Override
						public UserInfo transform(List<String> entry) throws Throwable {
							return new UserInfo(entry.get(0), entry.get(1), entry.get(2));
						}
					}
			)
		);
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

}
