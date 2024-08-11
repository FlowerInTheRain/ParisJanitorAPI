package fr.jypast.parisjanitorapi.bootstrap.client;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import fr.jypast.parisjanitorapi.bootstrap.PackagesAndLayers;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "fr.barlords.parisjanitorapi.client",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class ClientDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule CLIENT_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(PackagesAndLayers.CLIENT_PACKAGE)
          .should()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(PackagesAndLayers.CLIENT_PACKAGE, PackagesAndLayers.BOOTSTRAP_PACKAGE)
          .andShould()
          .onlyBeAccessed()
          .byClassesThat()
          .resideInAnyPackage(PackagesAndLayers.BOOTSTRAP_PACKAGE, PackagesAndLayers.CLIENT_PACKAGE)
          .andShould()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(PackagesAndLayers.BOOTSTRAP_PACKAGE, PackagesAndLayers.CLIENT_PACKAGE);

  @ArchTest
  public static final ArchRule controller_should_be_annotate_with_RestController =
          classes()
                  .that().resideInAPackage(PackagesAndLayers.CLIENT_PACKAGE)
                  .and().resideInAPackage("..client.controller..")
                  .should().beAnnotatedWith(RestController.class);

  @ArchTest
  static final ArchRule controller_should_end_with_Controller =
          classes()
                  .that().resideInAPackage(PackagesAndLayers.CLIENT_PACKAGE)
                  .and().resideInAPackage("..controller..")
                  .should().haveSimpleNameEndingWith("Controller");
}
