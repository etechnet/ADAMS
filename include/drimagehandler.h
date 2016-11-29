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

/***************************************************************************
                          drimagehandler.h  -  description
                             -------------------
    begin                : Mon Oct 2 2000
    copyright            : (C) 2000 by Piergiorgio Betti
    email                : pbetti@lpconsul.net
 ***[ History ]*************************************************************

   - Date - - By ---------------- - What -----------------------------------
 ***************************************************************************/


#ifndef DRIMAGEHANDLER_H
#define DRIMAGEHANDLER_H

#include "drinterface.h"
#include "applogger.h"


#define DRIMAGEHANDLER_ARRAY_BUFFER_SIZE	8192

union UnionFieldValue {
	unsigned int	fvUnsignedInt;
	int		fvInt;
	unsigned short	fvUnsignedShort;
	short		fvShort;
	unsigned long	fvUnsignedLong;
	long		fvLong;
	void *		fvPointer;
	unsigned char	fvUnsignedChar;
	unsigned char *	fvArrayOfChar;
	float		fvFloat;
	double		fvDouble;
};

struct __wrapUnionFieldValue {
	UnionFieldValue ufv;
#ifdef __GNUC__
} __attribute__((packed)) ;
#else
};
#endif

typedef struct __wrapUnionFieldValue wrapUnionFieldValue;


/** Questa classe implementa metodi per l'accesso "fast" ai campi del cartellino.
  * Viene caricata con una immagine del record cartellino
  * e da questo estrae i singoli campi "on-demand"
  *@short Cache di accesso ai campi cartellino
  *@author Piergiorgio Betti <pbetti@lpconsul.net>
 */

class DRImageHandler
{
public:
	DRImageHandler(DRInterface * dri, unsigned char * ri = 0, const unsigned int rs = 0) { dr_p = dri; RecordImage = ri; RecordSize = rs; }
	~DRImageHandler() { }

		/** Definisce la dimensione in bytes del record cartellino */
	inline void setSize(unsigned int size) { RecordSize = size; }
		/** Definisce la dimensione in bytes del record cartellino */
	inline void setImage(unsigned char * ri) { RecordImage = ri; }
		/** Ritorna la dimensione in bytes definita per il cartellino */
	inline unsigned int size() { return RecordSize; }
		/** Torna un puntatore alla cache interna immagine del cartellino */
	inline const unsigned char * getImageBuffer() { return RecordImage; }
		/** Torna un puntatore alla DRInterface utilizzata */
	inline DRInterface * getDRInterface() { return dr_p; }

		/** Accede al campo del cartellino e ne torna il valore, i valori sono prelevati
		  * dalla cache interna di default.
		  * @param idx Indice del campo del cartellino come da configurazione
		  */
	inline const union UnionFieldValue & field(int idx)
	{
		if (idx < 0) {
			lout << "Called DRImageHandler::getField with negative index" << endl;
			::exit(1);
		}
		DRField * Campo = dr_p->fastget(idx);
		wrapUnionFieldValue * pw = (wrapUnionFieldValue *)(RecordImage + Campo->data.offset);

		switch (Campo->data.fieldtype) {
			case DRF_unsigned_int:
			case DRF_int:
				Valore.fvUnsignedInt = pw->ufv.fvUnsignedInt;
				break;
			case DRF_unsigned_long:
			case DRF_long:
				Valore.fvUnsignedLong = pw->ufv.fvUnsignedLong;
				break;
			case DRF_unsigned_char:
			case DRF_char:
			case DRF_String:
			case DRF_CString:
				if (Campo->data.is_array) {
					Valore.fvArrayOfChar = ArrayBuffer;
					memcpy(ArrayBuffer, (RecordImage + Campo->data.offset), Campo->data.array_size);
					ArrayBuffer[Campo->data.array_size] = '\0';
				}
				else
					Valore.fvUnsignedChar = pw->ufv.fvUnsignedChar;
				break;
			case DRF_BCD:
				Valore.fvArrayOfChar = ArrayBuffer;
				BCDToString(ArrayBuffer, (RecordImage + Campo->data.offset), Campo->data.array_size);
// 				rlout << "::" << Campo->data.offset << ", " << Campo->data.array_size << ", [" << (char *)Valore.fvArrayOfChar << "]" << endl;
				break;
			case DRF_unsigned_short:
			case DRF_short:
				Valore.fvUnsignedShort = pw->ufv.fvUnsignedShort;
				break;
			case DRF_float:
				Valore.fvFloat = pw->ufv.fvFloat;
				break;
			case DRF_double:
				Valore.fvDouble = pw->ufv.fvDouble;
				break;
			default:
				lout << "DRImageHandler::getField : got unknown field type " << Campo->data.fieldtype << endl;
				break;
		}
		return Valore;
	}

	const union UnionFieldValue & field(int kind, int offset, bool isarr = false, int arrsize = 0)
	{
		wrapUnionFieldValue * pw = (wrapUnionFieldValue *)(RecordImage + offset);

		switch (kind) {
			case DRF_unsigned_int:
			case DRF_int:
				Valore.fvUnsignedInt = pw->ufv.fvUnsignedInt;
				break;
			case DRF_unsigned_long:
			case DRF_long:
				Valore.fvUnsignedLong = pw->ufv.fvUnsignedLong;
				break;
			case DRF_unsigned_char:
			case DRF_char:
			case DRF_CString:
			case DRF_String:
				if (isarr) {
					Valore.fvArrayOfChar = ArrayBuffer;
					memcpy(Valore.fvArrayOfChar, (RecordImage + offset), arrsize);
					Valore.fvArrayOfChar[arrsize] = '\0';
				}
				else
					Valore.fvUnsignedChar = pw->ufv.fvUnsignedChar;
				break;
			case DRF_BCD:
				Valore.fvArrayOfChar = ArrayBuffer;
				BCDToString(ArrayBuffer, (RecordImage + offset), arrsize);
				break;
			case DRF_unsigned_short:
			case DRF_short:
				Valore.fvUnsignedShort = pw->ufv.fvUnsignedShort;
				break;
			case DRF_float:
				Valore.fvFloat = pw->ufv.fvFloat;
				break;
			case DRF_double:
				Valore.fvDouble = pw->ufv.fvDouble;
				break;
			default:
				lout << "DRImageHandler::getField : got unknown field type " << kind << endl;
				break;
		}
		return Valore;
	}

	inline void BCDToString(unsigned char * dest, unsigned char * src, int srcSize)
	{
		int asciibase;
		for (int i = 0; i < srcSize; i++) {
			asciibase = (((src[i] & 0xf0)>>4) >= 0x0a) ? ('A' - 0x0a) : '0';
			*dest = ((src[i] & 0xf0)>>4) + asciibase; ++dest;
			asciibase = (((src[i] & 0x0f)) >= 0x0a) ? ('A' - 0x0a) : '0';
			*dest = (src[i] & 0x0f) + asciibase; ++dest;
		}
// 		dest[srcSize*2] = '\0';
		*dest = '\0';
	}

//		/** Accede al campo del cartellino e ne torna il valore. Differisce dalla
//		  * @ref field in quanto l'iimagine del cartellino viene fornita dall'esterno.
//		  * @param idx Indice del campo del cartellino come da configurazione
//		  * @param WrkRecordImage Immagine del record cartellino
//		  */
//	const union UnionFieldValue & getField(int idx, unsigned char * WrkRecordImage);
	bool validate(DRInterface * dri);
private:
	unsigned char * RecordImage;
	unsigned int RecordSize;
	union UnionFieldValue Valore;
	unsigned char ArrayBuffer[DRIMAGEHANDLER_ARRAY_BUFFER_SIZE];
// 	unsigned char ConversionBuffer[DRIMAGEHANDLER_ARRAY_BUFFER_SIZE/2];
	DRInterface * dr_p;
};


#endif
