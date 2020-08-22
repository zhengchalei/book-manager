package cn.runjava.book;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("cn.runjava.book");

        noClasses()
            .that()
            .resideInAnyPackage("cn.runjava.book.service..")
            .or()
            .resideInAnyPackage("cn.runjava.book.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..cn.runjava.book.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
