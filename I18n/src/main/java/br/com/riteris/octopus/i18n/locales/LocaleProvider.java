package br.com.riteris.octopus.i18n.locales;

import java.util.Locale;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public enum LocaleProvider {

    SQ_AL( "Albanian", "Albania", "sq", "AL" ),
    AR_DZ( "Arabic", "Algeria", "ar", "DZ" ),
    AR_BH( "Arabic", "Bahrain", "ar", "BH" ),
    AR_EG( "Arabic", "Egypt", "ar", "EG" ),
    AR_IQ( "Arabic", "Iraq", "ar", "IQ" ),
    AR_JO( "Arabic", "Jordan", "ar", "JO" ),
    AR_KW( "Arabic", "Kuwait", "ar", "KW" ),
    AR_LB( "Arabic", "Lebanon", "ar", "LB" ),
    AR_LY( "Arabic", "Libya", "ar", "LY" ),
    AR_MA( "Arabic", "Morocco", "ar", "MA" ),
    AR_OM( "Arabic", "Oman", "ar", "OM" ),
    AR_QA( "Arabic", "Qatar", "ar", "QA" ),
    AR_SA( "Arabic", "Saudi Arabia", "ar", "SA" ),
    AR_SD( "Arabic", "Sudan", "ar", "SD" ),
    AR_SY( "Arabic", "Syria", "ar", "SY" ),
    AR_TN( "Arabic", "Tunisia", "ar", "TN" ),
    AR_AE( "Arabic", "United Arab Emirates", "ar", "AE" ),
    AR_YE( "Arabic", "Yemen", "ar", "YE" ),
    BE_BY( "Belarusian", "Belarus", "be", "BY" ),
    BG_BG( "Bulgarian", "Bulgaria", "bg", "BG" ),
    CA_ES( "Catalan", "Spain", "ca", "ES" ),
    ZH_CN( "Chinese (Simplified)", "China", "zh", "CN" ),
    ZH_SG( "Chinese (Simplified)", "Singapore", "zh", "SG" ),
    ZH_HK( "Chinese (Traditional)", "Hong Kong", "zh", "HK" ),
    ZH_TW( "Chinese (Traditional)", "Taiwan", "zh", "TW" ),
    HR_HR( "Croatian", "Croatia", "hr", "HR" ),
    CS_CZ( "Czech", "Czech Republic", "cs", "CZ" ),
    DA_DK( "Danish", "Denmark", "da", "DK" ),
    NL_BE( "Dutch", "Belgium", "nl", "BE" ),
    NL_NL( "Dutch", "Netherlands", "nl", "NL" ),
    EN_AU( "English", "Australia", "en", "AU" ),
    EN_CA( "English", "Canada", "en", "CA" ),
    EN_IN( "English", "India", "en", "IN" ),
    EN_IE( "English", "Ireland", "en", "IE" ),
    EN_MT( "English", "Malta", "en", "MT" ),
    EN_NZ( "English", "New Zealand", "en", "NZ" ),
    EN_PH( "English", "Philippines", "en", "PH" ),
    EN_SG( "English", "Singapore", "en", "SG" ),
    EN_ZA( "English", "South Africa", "en", "ZA" ),
    EN_GB( "English", "United Kingdom", "en", "GB" ),
    EN_US( "English", "United States", "en", "US" ),
    ET_EE( "Estonian", "Estonia", "et", "EE" ),
    FI_FI( "Finnish", "Finland", "fi", "FI" ),
    FR_BE( "French", "Belgium", "fr", "BE" ),
    FR_CA( "French", "Canada", "fr", "CA" ),
    FR_FR( "French", "France", "fr", "FR" ),
    FR_LU( "French", "Luxembourg", "fr", "LU" ),
    FR_CH( "French", "Switzerland", "fr", "CH" ),
    DE_AT( "German", "Austria", "de", "AT" ),
    DE_DE( "German", "Germany", "de", "DE" ),
    DE_LU( "German", "Luxembourg", "de", "LU" ),
    DE_CH( "German", "Switzerland", "de", "CH" ),
    EL_CY( "Greek", "Cyprus", "el", "CY" ),
    EL_GR( "Greek", "Greece", "el", "GR" ),
    IW_IL( "Hebrew", "Israel", "iw", "IL" ),
    HI_IN( "Hindi", "India", "hi", "IN" ),
    HU_HU( "Hungarian", "Hungary", "hu", "HU" ),
    IS_IS( "Icelandic", "Iceland", "is", "IS" ),
    IN_ID( "Indonesian", "Indonesia", "in", "ID" ),
    GA_IE( "Irish", "Ireland", "ga", "IE" ),
    IT_IT( "Italian", "Italy", "it", "IT" ),
    IT_CH( "Italian", "Switzerland", "it", "CH" ),
    JA_JP( "Japanese (Gregorian calendar)", "Japan", "ja", "JP" ),
    JA_JP_JP( "Japanese (Imperial calendar)", "Japan", "ja_JP", "JP" ),
    KO_KR( "Korean", "South Korea", "ko", "KR" ),
    LV_LV( "Latvian", "Latvia", "lv", "LV" ),
    LT_LT( "Lithuanian", "Lithuania", "lt", "LT" ),
    MK_MK( "Macedonian", "Macedonia", "mk", "MK" ),
    MS_MY( "Malay", "Malaysia", "ms", "MY" ),
    MT_MT( "Maltese", "Malta", "mt", "MT" ),
    NO_NO( "Norwegian (Bokmål)", "Norway", "no", "NO" ),
    NO_NO_NY( "Norwegian (Nynorsk)", "Norway", "no_NO", "NY" ),
    PL_PL( "Polish", "Poland", "pl", "PL" ),
    PT_BR( "Portuguese", "Brazil", "pt", "BR" ),
    PT_PT( "Portuguese", "Portugal", "pt", "PT" ),
    RO_RO( "Romanian", "Romania", "ro", "RO" ),
    RU_RU( "Russian", "Russia", "ru", "RU" ),
    SR_BA( "Serbian (Cyrillic)", "Bosnia and Herzegovina", "sr", "BA" ),
    SR_ME( "Serbian (Cyrillic)", "Montenegro", "sr", "ME" ),
    SR_RS( "Serbian (Cyrillic)", "Serbia", "sr", "RS" ),
    SR_LATN_BA( "Serbian (Latin)", "Bosnia and Herzegovina", "sr_Latn", "BA" ),
    SR_LATN_ME( "Serbian (Latin)", "Montenegro", "sr_Latn", "ME" ),
    SR_LATN_RS( "Serbian (Latin)", "Serbia", "sr_Latn", "RS" ),
    SK_SK( "Slovak", "Slovakia", "sk", "SK" ),
    SL_SI( "Slovenian", "Slovenia", "sl", "SI" ),
    ES_AR( "Spanish", "Argentina", "es", "AR" ),
    ES_BO( "Spanish", "Bolivia", "es", "BO" ),
    ES_CL( "Spanish", "Chile", "es", "CL" ),
    ES_CO( "Spanish", "Colombia", "es", "CO" ),
    ES_CR( "Spanish", "Costa Rica", "es", "CR" ),
    ES_DO( "Spanish", "Dominican Republic", "es", "DO" ),
    ES_EC( "Spanish", "Ecuador", "es", "EC" ),
    ES_SV( "Spanish", "El Salvador", "es", "SV" ),
    ES_GT( "Spanish", "Guatemala", "es", "GT" ),
    ES_HN( "Spanish", "Honduras", "es", "HN" ),
    ES_MX( "Spanish", "Mexico", "es", "MX" ),
    ES_NI( "Spanish", "Nicaragua", "es", "NI" ),
    ES_PA( "Spanish", "Panama", "es", "PA" ),
    ES_PY( "Spanish", "Paraguay", "es", "PY" ),
    ES_PE( "Spanish", "Peru", "es", "PE" ),
    ES_PR( "Spanish", "Puerto Rico", "es", "PR" ),
    ES_ES( "Spanish", "Spain", "es", "ES" ),
    ES_US( "Spanish", "United States", "es", "US" ),
    ES_UY( "Spanish", "Uruguay", "es", "UY" ),
    ES_VE( "Spanish", "Venezuela", "es", "VE" ),
    SV_SE( "Swedish", "Sweden", "sv", "SE" ),
    TH_TH( "Thai (Western digits)", "Thailand", "th", "TH" ),
    TH_TH_TH( "Thai (Thai digits)", "Thailand", "th_TH", "TH" ),
    TR_TR( "Turkish", "Turkey", "tr", "TR" ),
    UK_UA( "Ukrainian", "Ukraine", "uk", "UA" ),
    VI_VN( "Vietnamese", "Vietnam", "vi", "VN" );

    private final String languageName;

    private final String countryName;

    private final String languageCode;

    private final String countryCode;

    private final Locale locale;

    LocaleProvider( final String languageName, final String countryName, final String languageCode, final String countryCode ) {
        this.languageName = languageName;
        this.countryName = countryName;
        this.languageCode = languageCode;
        this.countryCode = countryCode;
        this.locale = new Locale( this.languageCode, this.countryCode );
    }

    public static Locale tryToGetLocaleByCode( String code ) {
        if ( stringIsNullOrEmptyOrBlank( code ) ) {
            throw new IllegalArgumentException( "The language code can't be null, empty or blank." );
        }

        code = code.replace( "-", "_" );
        code = code.replace( "/", "_" );

        if ( !code.contains( "_" ) ) {
            for ( LocaleProvider localeProvider : LocaleProvider.values() ) {
                if ( localeProvider.languageCode.equalsIgnoreCase( code ) || localeProvider.countryCode.equalsIgnoreCase( code ) ) {
                    return localeProvider.locale;
                }
            }
        } else {
            if ( code.split( "_" ).length > 3 ) {
                throw new IllegalArgumentException( "The language code is malformed." );
            }

            final LocaleProvider localeProvider = LocaleProvider.valueOf( code.toUpperCase() );

            if ( localeProvider != null ) {
                return localeProvider.locale;
            }
        }

        return null;
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public String getCountryName() {
        return this.countryName;
    }

}