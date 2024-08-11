package fr.jypast.parisjanitorapi.bootstrap.server;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import fr.jypast.parisjanitorapi.bootstrap.PackagesAndLayers;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "fr.barlords.parisjanitorapi.server",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class ServerDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule SERVER_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(PackagesAndLayers.SERVER_PACKAGE)
          .should()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(PackagesAndLayers.SERVER_PACKAGE, PackagesAndLayers.BOOTSTRAP_PACKAGE)
          .andShould()
          .onlyBeAccessed()
          .byAnyPackage(PackagesAndLayers.BOOTSTRAP_PACKAGE, PackagesAndLayers.SERVER_PACKAGE);

  @ArchTest
  public static final ArchRule repository_should_end_with_repository =
          classes()
                  .that().resideInAPackage(PackagesAndLayers.SERVER_PACKAGE)
                  .and().resideInAPackage("..repository..")
                  .should().haveSimpleNameEndingWith("Repository");

  @ArchTest
  public static final ArchRule adapter_should_end_with_adapter =
          classes()
                  .that().resideInAPackage(PackagesAndLayers.SERVER_PACKAGE)
                  .and().resideInAPackage("..adapter..")
                  .should().haveSimpleNameEndingWith("Adapter");
}
