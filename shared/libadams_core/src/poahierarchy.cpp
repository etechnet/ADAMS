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


//--------
// #include's
//--------
#include "poahierarchy.h"
#include <assert.h>
#include "p_strstream.h"


//----------------------------------------------------------------------
// Function:    Constructor
//
// Description: Create the POA hierarchy.
//----------------------------------------------------------------------

PoaHierarchy::PoaHierarchy(CORBA::ORB_ptr orb, const char * poa_name) throw(PoaUtilityException)
: PoaUtility(orb, RANDOM_PORTS_WITH_IMR)
{
	//--------
	// Create the labelled POA Managers
	//--------
	m_core_functionality = createPoaManager("core_functionality");

	//--------
	// Create the named POA
	//--------
	m_Core = createPoa(poa_name,
		           root(), m_core_functionality,
		           "user_id + persistent");

}





//----------------------------------------------------------------------
// Function:    Destructor
//
// Description:
//----------------------------------------------------------------------

PoaHierarchy::~PoaHierarchy()
{
	//--------
	// Nothing to do.
	//--------
}
