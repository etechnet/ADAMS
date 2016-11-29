/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Author Name <author.name@e-tech.net>
#
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
#
#  HISTORY:
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/

#ifndef CONVERTER_H
#define CONVERTER_H

#include <stdlib.h>

#define CONVERTER_BUFFER_SIZE	2048
#define FLOAT_DIGIT_NUM		3

/** In questa classe vengono definiti dei metodi per convesione numerica/ascii. L'obiettivo
  * sono in particolare le performance, infatti questa classe viene utilizzata principalmente
  * all'interno della classe @ref builder del ntmdataserver, ovvero nel set di routine pi�
  * ottimizzate del progetto ADAMS.
  *@short Conversione numerico/ascii.
  *@author Piergiorgio Betti <pbetti@lpconsul.net>
  */


class converter {
public:
	converter() {}
	~converter() {}

	/** Metodo inlined per eseguire la conversione dal formato unsigned long in ascii.
	  * @param num    Valore in entrata da convertire
	  */
	inline unsigned char * decimalLongToAscii ( unsigned long num ) {
		int i;
		unsigned char * p = convBuffer + CONVERTER_BUFFER_SIZE - 1;

		*p-- = '\0';
		do {
			i = ( int ) ( num % 10 );
			i += '0';
			*p-- = i;
		}
		while ( num = num / 10 );
		return p + 1;
	}

	/** Metodo inlined (overload) per eseguire la conversione dal formato unsigned long in ascii.
	  * @param num    Valore in entrata da convertire
	  */
	inline char * decimalLongToAscii ( unsigned long num, char * _place, int size ) {
		int i;
		char * p = _place + size - 1;

		do {
			i = ( int ) ( num % 10 );
			i += '0';
			*p-- = i;
		}
		while ( ( num = num / 10 ) && ( p >= _place ) );
		return p;
	}

	/** Metodo inlined per eseguire la conversione dal formato unsigned long in ascii.
	  * Si differenzia dal precedente @ref decimalLongToAscii in quanto il formato di conversione
	  * � esadecimale.
	  * @param num    Valore in entrata da convertire
	  */
	inline unsigned char * hexadecimalLongToAscii ( unsigned long num ) {
		int i;
		unsigned char * p = convBuffer + CONVERTER_BUFFER_SIZE - 1;

		*p = '\0';
		do {
			i = ( int ) ( num % 16 );
			i += '0';
			if ( i > '9' ) i += 'A' - '0' - 10;
			*p-- = i;
		}
		while ( num = num / 16 );
		return p + 1;
	}

	/** Metodo inlined per eseguire la conversione dal formato double in ascii.
	  * @param num    Valore in entrata da convertire
	  */
	inline unsigned char * floatToAscii ( double num ) {
		int i, c, z;
		register unsigned char * p = ( unsigned char * ) fcvt ( num, FLOAT_DIGIT_NUM, &c, &i );
		register unsigned char * s = convBuffer;

		if ( i ) *s++ = '-';
		z = c;
		if ( c <= 0 ) {
			*s++ = '0';
			*s++ = '.';
			while ( z < 0 ) {
				*s++ = '0';
				++z;
			}
			--c;
		}
		else {
			do {
				*s++ = *p++;
			}
			while ( --c );
			*s++ = '.';
		}
		while ( *s++ = *p++ )
			;
		return convBuffer;
	}

	/** Metodo inlined utilizzato per convertire da formato long in ascii. La stringa
	  * di conversione generata viene giustificata a destra all'interno di un campo di
	  * "size" bytes riempito a sinistra con '0'.
	  * @param num    Numero in conversione signed long
	  * @param size   Ampiezza campo di giustificazione
	  */
	inline char * setLong ( long num, int size ) {
		unsigned char * s = decimalLongToAscii ( num );

		size -= convBuffer + CONVERTER_BUFFER_SIZE - s - 1;
		memset ( ( void * ) tempBuffer, ( int ) '0', size );
		while ( *s )
			tempBuffer[size++] = *s++;
		tempBuffer[size] = '\0';
		return ( char * ) tempBuffer;
	}

	/** Metodo inlined (overload) utilizzato per convertire da formato long in ascii. La stringa
	  * di conversione generata viene giustificata a destra all'interno di un campo di
	  * "size" bytes riempito a sinistra con '0'. La conversione viene effettuata sul buffer fornito
	  * dal chiamante.
	  * @param num    Numero in conversione signed long
	  * @param size   Ampiezza campo di giustificazione
	  * @param place  Buffer di conversione (contiene il risultato)
	  */

	inline char * setLong ( long num, int size, char * place ) {
		char * s = decimalLongToAscii ( num, place, size );

		while ( s >= place ) {
			*s-- = '0';
		}
		return place;
	}

	/** Metodo inlined utilizzato per convertire da formato long in ascii. La stringa
	  * di conversione generata viene giustificata a destra all'interno di un campo di
	  * "size" bytes riempito a sinistra con '0' utilizzando la base esadecimale.
	  * @param num    Numero in conversione signed long
	  * @param size   Ampiezza campo di giustificazione
	  */
	inline char * setHexLong ( long num, int size ) {
		unsigned char * s = hexadecimalLongToAscii ( num );
		size -= convBuffer + CONVERTER_BUFFER_SIZE - s - 1;
		memset ( ( void * ) tempBuffer, ( int ) '0', size );
		while ( *s )
			tempBuffer[size++] = *s++;
		tempBuffer[size] = '\0';
		return ( char * ) tempBuffer;
	}

	/** Metodo inlined utilizzato per convertire da formato double in ascii. La stringa
	  * di conversione generata viene giustificata a destra all'interno di un campo di
	  * "size" bytes riempito a sinistra con '0'.
	  * @param num    Numero in conversione double
	  * @param size   Ampiezza campo di giustificazione
	  */
	inline char * setDouble ( double num, int size, char * place ) {
		unsigned char * s = floatToAscii ( num );
		int sl = strlen ( ( const char * ) s );
		if ( sl > size ) {
			s += ( sl - size );
			*s = '<';
			sl = size;
		}
		size -= sl;
		memset ( ( void * ) place, ( int ) ' ', size );
		while ( *s )
			place[size++] = *s++;
		return place;
	}

private:
	unsigned char convBuffer[CONVERTER_BUFFER_SIZE];
	unsigned char tempBuffer[CONVERTER_BUFFER_SIZE];
};

#endif
