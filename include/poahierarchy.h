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

/*
 * #
 * #                $$$$$$$$                   $$
 * #                   $$                      $$
 * #  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
 * # $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
 * # $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
 * # $$                $$  $$        $$        $$    $$
 * #  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
 * #
 * #  MODULE DESCRIPTION:
 * #  <enter module description here>
 * #
 * #  AUTHORS:
 * #  Author Name <author.name@e-tech.net>
 * #
 * #  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory
 * #
 * #  HISTORY:
 * #  -[Date]- -[Who]------------- -[What]---------------------------------------
 * #  00.00.00 Author Name         Creation date
 * #--
 * #
 */


#ifndef POAHIERARCHY_H
#define POAHIERARCHY_H

//--------
// #include's
//--------
#include "poautility.h"
using namespace corbautil;


class PoaHierarchy
	: public PoaUtility
{
public:
	//--------
	// Constructor and destructor
	//--------
	PoaHierarchy(CORBA::ORB_ptr orb, const char *poa_name) throw(PoaUtilityException);
	virtual ~PoaHierarchy();

	//--------
	// Accessors
	//--------
	inline POAManager_ptr    core_functionality();
	inline POA_ptr           CoreProcessing();

private:
	//--------
	// Instance variables
	//--------
	corbautil::LabelledPOAManager	m_core_functionality;
	POA_var				m_Core;

	//--------
	// The following are not implemented
	//--------
	PoaHierarchy();
	PoaHierarchy(const PoaHierarchy &);
	PoaHierarchy & operator=(const PoaHierarchy &);
};



//--------
// Inline accessors
//--------

inline POAManager_ptr
PoaHierarchy::core_functionality()
{
	return m_core_functionality.mgr();
}


inline POA_ptr
PoaHierarchy::CoreProcessing()
{
	return m_Core;
}


#endif
