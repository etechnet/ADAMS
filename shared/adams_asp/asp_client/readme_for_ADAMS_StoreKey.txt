Per Generare il file ntmc_StoreKey (necessario per effettuare la firma):

**(da console)**

keytool -genkey -validity "730" -alias "STS" -keystore "ntmc_StoreKey"

Enter keystore password:	
==> ntmc_signer01

What is your first and last name? 	
==> STS NTM_CONF

What is the name of your organizational unit?
==> STS NTM_CONF

What is the name of your organization?
==> STS NTM_CONF

What is the name of your City or Locality?
==> Rome

What is the name of your State or Province?
==> Italy

What is the two-letter country code for this unit?
==> IT

Is CN=STS, OU=STS, O=STS, L=Rome, ST=Italy, C=IT correct?
==> yes

Enter key password for <STS>
        (RETURN if same as keystore password):
==> ntmc_signer02


*******************************************

(La firma e la verifica viene generata in automatico  nel Makefile)
**** Firma del file ntmconf.jar ****
jarsigner -keystore ntmc_StoreKey -storepass ntmc_signer01 -keypass ntmc_signer02 ntmconf.jar STS

**** Verifica della firma del file ntmconf.jar ****
jarsigner -verify ntmconf.jar


