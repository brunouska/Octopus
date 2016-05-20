package br.com.riteris.octopus.i18n.locales;

import br.com.riteris.octopus.utils.StringTools;

/**
 * Define os valores do enum que representa as diversas linguagens que podem estar disponíveis nos sistemas
 * desenvolvidos.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do Enum.
 * @since 1.0.0 - Criada em 17 de abr de 2016
 */
public enum Language {

    sq_AL( "Albanian", "Albania", "sq", "AL" ),
    ar_DZ( "Arabic", "Algeria", "ar", "DZ" ),
    ar_BH( "Arabic", "Bahrain", "ar", "BH" ),
    ar_EG( "Arabic", "Egypt", "ar", "EG" ),
    ar_IQ( "Arabic", "Iraq", "ar", "IQ" ),
    ar_JO( "Arabic", "Jordan", "ar", "JO" ),
    ar_KW( "Arabic", "Kuwait", "ar", "KW" ),
    ar_LB( "Arabic", "Lebanon", "ar", "LB" ),
    ar_LY( "Arabic", "Libya", "ar", "LY" ),
    ar_MA( "Arabic", "Morocco", "ar", "MA" ),
    ar_OM( "Arabic", "Oman", "ar", "OM" ),
    ar_QA( "Arabic", "Qatar", "ar", "QA" ),
    ar_SA( "Arabic", "Saudi Arabia", "ar", "SA" ),
    ar_SD( "Arabic", "Sudan", "ar", "SD" ),
    ar_SY( "Arabic", "Syria", "ar", "SY" ),
    ar_TN( "Arabic", "Tunisia", "ar", "TN" ),
    ar_AE( "Arabic", "United Arab Emirates", "ar", "AE" ),
    ar_YE( "Arabic", "Yemen", "ar", "YE" ),
    be_BY( "Belarusian", "Belarus", "be", "BY" ),
    bg_BG( "Bulgarian", "Bulgaria", "bg", "BG" ),
    ca_ES( "Catalan", "Spain", "ca", "ES" ),
    zh_CN( "Chinese (Simplified)", "China", "zh", "CN" ),
    zh_SG( "Chinese (Simplified)", "Singapore", "zh", "SG" ),
    zh_HK( "Chinese (Traditional)", "Hong Kong", "zh", "HK" ),
    zh_TW( "Chinese (Traditional)", "Taiwan", "zh", "TW" ),
    hr_HR( "Croatian", "Croatia", "hr", "HR" ),
    cs_CZ( "Czech", "Czech Republic", "cs", "CZ" ),
    da_DK( "Danish", "Denmark", "da", "DK" ),
    nl_BE( "Dutch", "Belgium", "nl", "BE" ),
    nl_NL( "Dutch", "Netherlands", "nl", "NL" ),
    en_AU( "English", "Australia", "en", "AU" ),
    en_CA( "English", "Canada", "en", "CA" ),
    en_IN( "English", "India", "en", "IN" ),
    en_IE( "English", "Ireland", "en", "IE" ),
    en_MT( "English", "Malta", "en", "MT" ),
    en_NZ( "English", "New Zealand", "en", "NZ" ),
    en_PH( "English", "Philippines", "en", "PH" ),
    en_SG( "English", "Singapore", "en", "SG" ),
    en_ZA( "English", "South Africa", "en", "ZA" ),
    en_GB( "English", "United Kingdom", "en", "GB" ),
    en_US( "English", "United States", "en", "US" ),
    et_EE( "Estonian", "Estonia", "et", "EE" ),
    fi_FI( "Finnish", "Finland", "fi", "FI" ),
    fr_BE( "French", "Belgium", "fr", "BE" ),
    fr_CA( "French", "Canada", "fr", "CA" ),
    fr_FR( "French", "France", "fr", "FR" ),
    fr_LU( "French", "Luxembourg", "fr", "LU" ),
    fr_CH( "French", "Switzerland", "fr", "CH" ),
    de_AT( "German", "Austria", "de", "AT" ),
    de_DE( "German", "Germany", "de", "DE" ),
    de_LU( "German", "Luxembourg", "de", "LU" ),
    de_CH( "German", "Switzerland", "de", "CH" ),
    el_CY( "Greek", "Cyprus", "el", "CY" ),
    el_GR( "Greek", "Greece", "el", "GR" ),
    iw_IL( "Hebrew", "Israel", "iw", "IL" ),
    hi_IN( "Hindi", "India", "hi", "IN" ),
    hu_HU( "Hungarian", "Hungary", "hu", "HU" ),
    is_IS( "Icelandic", "Iceland", "is", "IS" ),
    in_ID( "Indonesian", "Indonesia", "in", "ID" ),
    ga_IE( "Irish", "Ireland", "ga", "IE" ),
    it_IT( "Italian", "Italy", "it", "IT" ),
    it_CH( "Italian", "Switzerland", "it", "CH" ),
    ja_JP( "Japanese (Gregorian calendar)", "Japan", "ja", "JP" ),
    ja_JP_JP( "Japanese (Imperial calendar)", "Japan", "ja_JP", "JP" ),
    ko_KR( "Korean", "South Korea", "ko", "KR" ),
    lv_LV( "Latvian", "Latvia", "lv", "LV" ),
    lt_LT( "Lithuanian", "Lithuania", "lt", "LT" ),
    mk_MK( "Macedonian", "Macedonia", "mk", "MK" ),
    ms_MY( "Malay", "Malaysia", "ms", "MY" ),
    mt_MT( "Maltese", "Malta", "mt", "MT" ),
    no_NO( "Norwegian (Bokmål)", "Norway", "no", "NO" ),
    no_NO_NY( "Norwegian (Nynorsk)", "Norway", "no_NO", "NY" ),
    pl_PL( "Polish", "Poland", "pl", "PL" ),
    pt_BR( "Portuguese", "Brazil", "pt", "BR" ),
    pt_PT( "Portuguese", "Portugal", "pt", "PT" ),
    ro_RO( "Romanian", "Romania", "ro", "RO" ),
    ru_RU( "Russian", "Russia", "ru", "RU" ),
    sr_BA( "Serbian (Cyrillic)", "Bosnia and Herzegovina", "sr", "BA" ),
    sr_ME( "Serbian (Cyrillic)", "Montenegro", "sr", "ME" ),
    sr_RS( "Serbian (Cyrillic)", "Serbia", "sr", "RS" ),
    sr_Latn_BA( "Serbian (Latin)", "Bosnia and Herzegovina", "sr_Latn", "BA" ),
    sr_Latn_ME( "Serbian (Latin)", "Montenegro", "sr_Latn", "ME" ),
    sr_Latn_RS( "Serbian (Latin)", "Serbia", "sr_Latn", "RS" ),
    sk_SK( "Slovak", "Slovakia", "sk", "SK" ),
    sl_SI( "Slovenian", "Slovenia", "sl", "SI" ),
    es_AR( "Spanish", "Argentina", "es", "AR" ),
    es_BO( "Spanish", "Bolivia", "es", "BO" ),
    es_CL( "Spanish", "Chile", "es", "CL" ),
    es_CO( "Spanish", "Colombia", "es", "CO" ),
    es_CR( "Spanish", "Costa Rica", "es", "CR" ),
    es_DO( "Spanish", "Dominican Republic", "es", "DO" ),
    es_EC( "Spanish", "Ecuador", "es", "EC" ),
    es_SV( "Spanish", "El Salvador", "es", "SV" ),
    es_GT( "Spanish", "Guatemala", "es", "GT" ),
    es_HN( "Spanish", "Honduras", "es", "HN" ),
    es_MX( "Spanish", "Mexico", "es", "MX" ),
    es_NI( "Spanish", "Nicaragua", "es", "NI" ),
    es_PA( "Spanish", "Panama", "es", "PA" ),
    es_PY( "Spanish", "Paraguay", "es", "PY" ),
    es_PE( "Spanish", "Peru", "es", "PE" ),
    es_PR( "Spanish", "Puerto Rico", "es", "PR" ),
    es_ES( "Spanish", "Spain", "es", "ES" ),
    es_US( "Spanish", "United States", "es", "US" ),
    es_UY( "Spanish", "Uruguay", "es", "UY" ),
    es_VE( "Spanish", "Venezuela", "es", "VE" ),
    sv_SE( "Swedish", "Sweden", "sv", "SE" ),
    th_TH( "Thai (Western digits)", "Thailand", "th", "TH" ),
    th_TH_TH( "Thai (Thai digits)", "Thailand", "th_TH", "TH" ),
    tr_TR( "Turkish", "Turkey", "tr", "TR" ),
    uk_UA( "Ukrainian", "Ukraine", "uk", "UA" ),
    vi_VN( "Vietnamese", "Vietnam", "vi", "VN" );

    /**
     * Nome da linguagem.
     */
    private final String languageName;

    /**
     * Nome do país.
     */
    private final String countryName;

    /**
     * Código curto da linguagem.
     */
    private final String shortLanguageCode;

    /**
     * Código curto do país.
     */
    private final String shortCountryCode;

    /**
     * Construtor do objeto.
     *
     * @param languageName      {@link #languageName}.
     * @param countryName       {@link #countryName}.
     * @param shortLanguageCode {@link #shortLanguageCode}.
     * @param shortCountryCode  {@link #shortCountryCode}.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    Language( final String languageName, final String countryName, final String shortLanguageCode, final String
            shortCountryCode ) {
        this.languageName = languageName;
        this.countryName = countryName;
        this.shortLanguageCode = shortLanguageCode;
        this.shortCountryCode = shortCountryCode;
    }

    /**
     * Dado um código completo de idioma e país, retorna o valor do enum {@link Language} correspondente ao código
     * informado, ou nulo caso não seja localizado o
     * idioma correspondente.
     *
     * @param completeLanguageCode Código completo do idioma o qual desejamos obter o valor do enum {@link Language}
     *                             correspondente.
     *
     * @return Valor do enum {@link Language} correspondente ao código de idioma informado ou nulo caso não seja
     * localizado um idioma correspondente ao código.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public static Language getLanguageByCode( String completeLanguageCode ) {
        if ( StringTools.isNullEmptyOrBlank( completeLanguageCode ) ) {
            throw new IllegalArgumentException( "The complete language code must be informed." );
        }

        completeLanguageCode = completeLanguageCode.replace( "-", "_" );
        completeLanguageCode = completeLanguageCode.replace( "/", "_" );

        for ( Language language : Language.values() ) {
            if ( language.name().toUpperCase().equals( completeLanguageCode.toUpperCase() ) ) {
                return language;
            }
        }

        return null;
    }

    /**
     * Dado um código curto de idioma, retorna o valor do enum {@link Language} correspondente ao código informado, ou
     * nulo caso não seja localizado o idioma
     * correspondente. O valor retornado é referente ao primeiro valor do enum {@link Language} que corresponder ao
     * código informado.
     *
     * @param shortLanguageCode Código curto do idioma o qual desejamos obter o valor do enum {@link Language}
     *                          correspondente.
     *
     * @return Valor do enum {@link Language} correspondente ao código curto de idioma informado ou nulo caso não seja
     * localizado um idioma correspondente ao
     * código.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public static Language getLanguageByShortLanguageCode( final String shortLanguageCode ) {
        if ( StringTools.isNullEmptyOrBlank( shortLanguageCode ) ) {
            throw new IllegalArgumentException( "The language code must be informed." );
        }

        for ( Language language : Language.values() ) {
            if ( language.getShortLanguageCode().toUpperCase().equals( shortLanguageCode.toUpperCase() ) ) {
                return language;
            }
        }

        return null;
    }

    /**
     * Retorna o valor atual do parâmetro {@link #shortLanguageCode}.<br>
     *
     * @return Valor atual de {@link #shortLanguageCode}
     */
    public String getShortLanguageCode() {
        return this.shortLanguageCode;
    }

    /**
     * Dado um código curto de país, retorna o valor do enum {@link Language} correspondente ao código informado, ou
     * nulo caso não seja localizado o idioma
     * correspondente. O valor retornado é referente ao primeiro valor do enum {@link Language} que corresponder ao
     * código informado.
     *
     * @param shortCountryCode Código curto do país o qual desejamos obter o valor do enum {@link Language}
     *                         correspondente.
     *
     * @return Valor do enum {@link Language} correspondente ao código curto de país informado ou nulo caso não seja
     * localizado um idioma correspondente ao
     * código.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public static Language getLanguageByShortCountryCode( final String shortCountryCode ) {
        if ( StringTools.isNullEmptyOrBlank( shortCountryCode ) ) {
            throw new IllegalArgumentException( "The country code must be informed." );
        }

        for ( Language language : Language.values() ) {
            if ( language.getShortCountryCode().toUpperCase().equals( shortCountryCode.toUpperCase() ) ) {
                return language;
            }
        }

        return null;
    }

    /**
     * Retorna o valor atual do parâmetro {@link #shortCountryCode}.<br>
     *
     * @return Valor atual de {@link #shortCountryCode}
     */
    public String getShortCountryCode() {
        return this.shortCountryCode;
    }

    /**
     * Dado o nome de uma linguagem, retorna o valor do enum {@link Language} correspondente ao nome informado, ou
     * nulo caso não seja localizado o idioma
     * correspondente. O valor retornado é referente ao primeiro valor do enum {@link Language} que corresponder ao
     * nome da linguagem informado.
     *
     * @param languageName Nome da linguagem para o qual desejamos obter o valor do enum {@link Language}
     *                     correspondente.
     *
     * @return Valor do enum {@link Language} correspondente ao nome da linguagem informado ou nulo caso não seja
     * localizado um idioma correspondente ao nome.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public static Language getLanguageByName( final String languageName ) {
        if ( StringTools.isNullEmptyOrBlank( languageName ) ) {
            throw new IllegalArgumentException( "The language name must be informed." );
        }

        for ( Language language : Language.values() ) {
            if ( language.getLanguageName().toUpperCase().equals( languageName.toUpperCase() ) ) {
                return language;
            }
        }

        return null;
    }

    /**
     * Retorna o valor atual do parâmetro {@link #languageName}.<br>
     *
     * @return Valor atual de {@link #languageName}
     */
    public String getLanguageName() {
        return this.languageName;
    }

    /**
     * Retorna o valor atual do parâmetro {@link #countryName}.<br>
     *
     * @return Valor atual de {@link #countryName}
     */
    public String getCountryName() {
        return this.countryName;
    }

}