package eu.adampacholski.miniOffice;

import eu.adampacholski.miniOffice.countries.Countries;
import eu.adampacholski.miniOffice.countries.CountriesService;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSetting;
import eu.adampacholski.miniOffice.invoice.invoiceNrSetting.InvoiceNrSettingService;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatus;
import eu.adampacholski.miniOffice.invoice.invoiceStatus.InvoiceStatusService;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceType;
import eu.adampacholski.miniOffice.invoice.invoiceType.InvoiceTypeService;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory.ItemCategory;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemCategory.ItemCategoryService;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit.ItemUnit;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemUnit.ItemUnitService;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouse;
import eu.adampacholski.miniOffice.item.itemWarehouse.itemWarehouse.ItemWarehouseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Install implements CommandLineRunner {

    private final CountriesService countriesService;
    private final ItemWarehouseService itemWarehouseService;
    private final ItemUnitService itemUnitService;
    private final InvoiceStatusService invoiceStatusService;
    private final InvoiceNrSettingService invoiceNrSettingService;
    private final InvoiceTypeService invoiceTypeService;
    private final ItemCategoryService itemCategoryService;

    public Install(CountriesService countriesService, ItemWarehouseService itemWarehouseService, ItemUnitService itemUnitService, InvoiceStatusService invoiceStatusService, InvoiceNrSettingService invoiceNrSettingService, InvoiceTypeService invoiceTypeService, ItemCategoryService itemCategoryService) {
        this.countriesService = countriesService;
        this.itemWarehouseService = itemWarehouseService;
        this.itemUnitService = itemUnitService;
        this.invoiceStatusService = invoiceStatusService;
        this.invoiceNrSettingService = invoiceNrSettingService;
        this.invoiceTypeService = invoiceTypeService;
        this.itemCategoryService = itemCategoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Countries> countryList = countriesService.getAllCountries();
        if (countryList.size() == 0) {
            addCountries();
            addItemType();
            addUnit();
            addInvoiceStat();
            addInvoiceSetting();
            addInvoiceTyp();
            addCategory();

        }
    }

    public void addCategory(){
        itemCategoryService.addItemCategory(new ItemCategory("Telefony"));
        itemCategoryService.addItemCategory(new ItemCategory("Telewizory"));
        itemCategoryService.addItemCategory(new ItemCategory("Komputery"));
        itemCategoryService.addItemCategory(new ItemCategory("AGD"));

    }
    public void addInvoiceTyp(){
        invoiceTypeService.add(new InvoiceType("Faktura", "F"));
        invoiceTypeService.add(new InvoiceType("Korekta", "K"));
        invoiceTypeService.add(new InvoiceType("List przewozowy", "L"));
        invoiceTypeService.add(new InvoiceType("Oferta", "O"));
    }
    public void addInvoiceSetting() {
        invoiceNrSettingService.add(new InvoiceNrSetting(2023, 1));
        invoiceNrSettingService.add(new InvoiceNrSetting(2023, 1));
        invoiceNrSettingService.add(new InvoiceNrSetting(2023, 1));
        invoiceNrSettingService.add(new InvoiceNrSetting(2023, 1));

    }

    public void addInvoiceStat() {
        invoiceStatusService.add(new InvoiceStatus("Zapłacone"));
        invoiceStatusService.add(new InvoiceStatus("Niezapłacone"));
        invoiceStatusService.add(new InvoiceStatus("Anulowane"));
        invoiceStatusService.add(new InvoiceStatus("Korekta"));
    }

    public void addUnit() {
        itemUnitService.addItem(new ItemUnit("Sztuk"));
        itemUnitService.addItem(new ItemUnit("Kilo"));
        itemUnitService.addItem(new ItemUnit("Metr"));
    }

    public void addItemType() {
        itemWarehouseService.addItem(new ItemWarehouse("Usługa", 0));
        itemWarehouseService.addItem(new ItemWarehouse("Produkt", 1));
        itemWarehouseService.addItem(new ItemWarehouse("Informacja", 0));
        itemWarehouseService.addItem(new ItemWarehouse("Service", 0));
    }

    public void addCountries() {
        countriesService.addCountry(new Countries(48, "Polska"));
        countriesService.addCountry(new Countries(32, "Belgia"));
        countriesService.addCountry(new Countries(359, "Bułgaria"));
        countriesService.addCountry(new Countries(420, "Czechy"));
        countriesService.addCountry(new Countries(49, "Niemcy"));
        countriesService.addCountry(new Countries(45, "Dania"));
        countriesService.addCountry(new Countries(34, "Hiszpania"));
        countriesService.addCountry(new Countries(372, "Estonia"));
        countriesService.addCountry(new Countries(33, "Francja"));
        countriesService.addCountry(new Countries(358, "Finlandia"));
        countriesService.addCountry(new Countries(43, "Austria"));
        countriesService.addCountry(new Countries(357, "Cypr"));
        countriesService.addCountry(new Countries(44, "Wielka Brytania"));
        countriesService.addCountry(new Countries(30, "Grecja"));
        countriesService.addCountry(new Countries(36, "Węgry"));
        countriesService.addCountry(new Countries(39, "Włochy"));
        countriesService.addCountry(new Countries(353, "Irlandia"));
        countriesService.addCountry(new Countries(370, "Litwa"));
        countriesService.addCountry(new Countries(371, "Łotwa"));
        countriesService.addCountry(new Countries(31, "Holandia"));
        countriesService.addCountry(new Countries(351, "Portugalia"));
        countriesService.addCountry(new Countries(46, "Szwecja"));
        countriesService.addCountry(new Countries(421, "Słowacja"));
        countriesService.addCountry(new Countries(386, "Słowenia"));
        countriesService.addCountry(new Countries(380, "Ukraina"));

    }
}
