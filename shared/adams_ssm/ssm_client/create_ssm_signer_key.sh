#!/bin/sh

# This sample script depicts the sequence of steps for using keytool
# to create self-signed client and server certificates for use with ADAMS
#
# Note that this script assumes that private keys and public certificates
# are stored in the same keystore file (client and server).  Public
# certificates can be stored in separate, password-protected truststores.

# Setup common environment variables. Project ADAMS ASP

CLIENT_DNAME="cn=ADAMS System Service Manager Client,ou=ASP,o=e-Tech,c=IT"
CLIENT_ALIAS="adams_ssm"
CLIENT_PW="ssm_signer_pw_1"
CLIENT_PW2="ssm_signer_pw_2"
CLIENT_KEYSTORE="$CLIENT_ALIAS".jks
CLIENT_CERTFILE="$CLIENT_ALIAS".cert

SERVER_DNAME="cn=ADAMS System Service Manager Server,ou=ASP,o=e-Tech,c=IT"
SERVER_ALIAS="ssm_server"
SERVER_PW="ssm_signer_pw_1"
SERVER_KEYSTORE="$SERVER_ALIAS".jks
SERVER_CERTFILE="$SERVER_ALIAS".cert
SERVER_NCAUTH_KEYSTORE="$SERVER_ALIAS"_ncauth.jks

DAYS_VALID=365

set -x

# Generate client's keypair and keystore
# Also creates a self-signed public key certificate for the client

keytool -genkey -validity "$DAYS_VALID" -keypass "$CLIENT_PW" \
-dname "$CLIENT_DNAME" -alias "$CLIENT_ALIAS" \
-keystore "$CLIENT_KEYSTORE" -storepass "$CLIENT_PW2"

# Export client's public key certificate to a file
keytool -export -keystore "$CLIENT_KEYSTORE" -storepass "$CLIENT_PW2" \
-alias "$CLIENT_ALIAS" -rfc -file "$CLIENT_CERTFILE"

# Generate server's keypair and keystore
# Also creates a self-signed public key certificate for the server
# A server always requires a keypair, unlike the client

# keytool -genkey -validity "$DAYS_VALID" -keypass "$SERVER_PW" \
# -dname "$SERVER_DNAME" -alias "$SERVER_ALIAS" \
# -keystore "$SERVER_KEYSTORE" -storepass "$SERVER_PW"

# Export server's public key certificate to a file
# keytool -export -keystore "$SERVER_KEYSTORE" -storepass "$SERVER_PW" \
# -alias "$SERVER_ALIAS" -rfc -file "$SERVER_CERTFILE"

# Import server's public key certificate into client's keystore as a
# new trusted certificate.
# keytool -import -keystore "$CLIENT_KEYSTORE" -storepass "$CLIENT_PW2" \
# -alias "$SERVER_ALIAS" -file "$SERVER_CERTFILE" -noprompt

# Make a copy of the server keystore before adding the client's certificate
# This can be used to test the case where no client authentication is required
# cp "$SERVER_KEYSTORE" "$SERVER_NCAUTH_KEYSTORE"

# Import client's public key certificate into the server's keystore as a
# new trusted certificate.  This is required only when client authentication
# is required by the server.
# keytool -import -keystore "$SERVER_KEYSTORE" -storepass "$SERVER_PW" \
# -alias "$CLIENT_ALIAS" -file "$CLIENT_CERTFILE" -noprompt

