//----------------------------------------------------------------------
// Copyright 2008 Ciaran McHale.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// 	The above copyright notice and this permission notice shall be
// 	included in all copies or substantial portions of the Software.
//
// 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// 	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// 	OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// 	NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// 	HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// 	WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// 	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// 	OTHER DEALINGS IN THE SOFTWARE.
//
// File:	PoaUtility.h
//
// Description: Class that provides operations to simplify the building
//		of POA hierarchies
//----------------------------------------------------------------------

#ifndef POA_UTILITY_H_
#define POA_UTILITY_H_





//--------
// #include's
//--------
#include "p_poa.h"
#include "p_strstream.h"
using namespace PortableServer;
#if defined(P_USE_ORBIX)
#include "p_orb_config.h"
#endif



namespace corbautil
{
	//--------
	// Exception thrown by some of the public APIs of PoaUtility.
	//--------
	class PoaUtilityException {
	public:
		PoaUtilityException()
		{
			msg = CORBA::string_dup("");
		}

		PoaUtilityException(std::strstream & buf)
		{
			msg = CORBA::string_dup(buf.str());
			buf.rdbuf()->freeze(0);
		}
		PoaUtilityException(const char * str)
		{
			msg = CORBA::string_dup(str);
		}

		CORBA::String_var	msg;
	};

	//--------
	// Data-types
	//--------
	class LabelledPOAManager {
	public:
		const char *		label() { return m_label.in(); }
		POAManager_ptr		mgr()   { return m_mgr.in(); }
	private:
		CORBA::String_var	m_label;
		POAManager_var		m_mgr;
		POA_var			m_helper_poa;
		friend class PoaUtility;
	};

#if defined(P_USE_ORBIX)
	struct LabelledOrbixWorkQueue {
	public:
		const char *		label() { return m_label.in(); }
		WorkQueue_ptr		wq()    { return m_wq.in(); }
	private:
		CORBA::String_var	m_label;
		WorkQueue_var		m_wq;
		friend class PoaUtility;
	};
#endif


	class PoaUtility
	{
	public:
		enum DeploymentModel {
			RANDOM_PORTS_NO_IMR,
			RANDOM_PORTS_WITH_IMR,
			FIXED_PORTS_NO_IMR,
			FIXED_PORTS_WITH_IMR
		};

		//--------
		// Convert the (case insensitive) stringified version of
		// a deployment model to the corresponding enum value.
		//--------
		static DeploymentModel
			stringToDeploymentModel(const char * model)
						throw(PoaUtilityException);

		//--------
		// Constructor and destructor
		//--------
		PoaUtility(CORBA::ORB_ptr orb, DeploymentModel deployModel)
			throw(PoaUtilityException);
		virtual ~PoaUtility();

		//--------
		// Portable public API
		//--------
		POA_ptr		   createPoa(
					const char *		poa_name,
					POA_ptr			parent_poa,
					LabelledPOAManager &	labelled_mgr,
					const char *		policies_str)
			throw(PoaUtilityException);

		LabelledPOAManager createPoaManager(
					const char *		label)
			throw(PoaUtilityException);

		POA_ptr		   root()	{ return m_root; }

		//--------
		// Additional Orbix-specific public API
		//--------
#if defined(P_USE_ORBIX)
		LabelledOrbixWorkQueue createAutoWorkQueue(
				const char *	label,
				CORBA::Long	max_size,
				CORBA::ULong	initial_thread_count,
				CORBA::Long	high_water_mark,
				CORBA::Long	low_water_mark,
				CORBA::Long	thread_stack_size_kb)
			throw(PoaUtilityException);

		LabelledOrbixWorkQueue createManualWorkQueue(
				const char *	label,
				CORBA::Long	max_size)
			throw(PoaUtilityException);

		POA_ptr
			createPoa(
				const char *			poa_name,
				POA_ptr				parent_poa,
				LabelledPOAManager &		labelled_mgr,
				const char *			policies_str,
				LabelledOrbixWorkQueue &	wq)
			throw(PoaUtilityException);
#endif

	private:
		//--------
		// Portable implementation details
		//--------
		char *	getFullPoaName(
				const char *	local_name,
				POA_ptr		parent_poa);

		CORBA::PolicyList * createPolicyList(
				const char *	poa_mgr_label,
				const char *	policy_list_str)
					throw(PoaUtilityException);

		POA_var			m_root;
		CORBA::ORB_var		m_orb;
		CORBA::Boolean		m_firstPoaMgr;
		CORBA::Boolean		m_useFixedPorts;
		DeploymentModel		m_deployModel;
		int			m_poa_mgr_count;

		//--------
		// Orbix-specific implementation details
		//--------
#if defined(P_USE_ORBIX)
		IT_Config::Configuration_var    m_cfg;

		CORBA::Long getConfigLong(
					const char *	the_namespace,
					const char *	entry_name,
					CORBA::Long	default_value);
#endif

		//--------
		// ORBacus-specific implementation details
		//--------
#if defined(P_USE_ORBACUS) && P_ORBACUS_VERSION < 420
		OBPortableServer::POAManagerFactory_var	m_poaMgrFactory;
#endif
#if defined(P_USE_ORBACUS) && P_ORBACUS_VERSION >= 420
		PortableServer::POAManagerFactory_var	m_poaMgrFactory;
#endif
	};

}; // namespace corbautil


inline std::ostream& operator << (
	std::ostream &				out,
	const corbautil::PoaUtilityException &	ex)
{
	out	<< ex.msg.in();
	return out;
}


#endif /* POA_UTILITY_H_ */
