// -*- C++ -*-
// $Id$

/**
 * Code generated by the The ACE ORB (TAO) IDL Compiler v2.2.2
 * TAO and the TAO IDL Compiler have been developed by:
 *       Center for Distributed Object Computing
 *       Washington University
 *       St. Louis, MO
 *       USA
 *       http://www.cs.wustl.edu/~schmidt/doc-center.html
 * and
 *       Distributed Object Computing Laboratory
 *       University of California at Irvine
 *       Irvine, CA
 *       USA
 * and
 *       Institute for Software Integrated Systems
 *       Vanderbilt University
 *       Nashville, TN
 *       USA
 *       http://www.isis.vanderbilt.edu/
 *
 * Information about TAO is available at:
 *     http://www.cs.wustl.edu/~schmidt/TAO.html
 **/


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::DATA_CENTRALI::_Descrizione_forany>::free (
    net::etech::DATA_CENTRALI::_Descrizione_slice * _tao_slice
  )
{
  net::etech::DATA_CENTRALI::_Descrizione_free (_tao_slice);
}

ACE_INLINE
net::etech::DATA_CENTRALI::_Descrizione_slice *
TAO::Array_Traits<net::etech::DATA_CENTRALI::_Descrizione_forany>::dup (
    const net::etech::DATA_CENTRALI::_Descrizione_slice * _tao_slice
  )
{
  return net::etech::DATA_CENTRALI::_Descrizione_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::DATA_CENTRALI::_Descrizione_forany>::copy (
    net::etech::DATA_CENTRALI::_Descrizione_slice * _tao_to,
    const net::etech::DATA_CENTRALI::_Descrizione_slice * _tao_from
  )
{
  net::etech::DATA_CENTRALI::_Descrizione_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::DATA_CENTRALI::_Descrizione_forany>::zero (
    net::etech::DATA_CENTRALI::_Descrizione_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 30; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::DATA_CENTRALI::_Descrizione_slice *
TAO::Array_Traits<net::etech::DATA_CENTRALI::_Descrizione_forany>::alloc (void)
{
  return net::etech::DATA_CENTRALI::_Descrizione_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::logProcess::_descr_forany>::free (
    net::etech::logProcess::_descr_slice * _tao_slice
  )
{
  net::etech::logProcess::_descr_free (_tao_slice);
}

ACE_INLINE
net::etech::logProcess::_descr_slice *
TAO::Array_Traits<net::etech::logProcess::_descr_forany>::dup (
    const net::etech::logProcess::_descr_slice * _tao_slice
  )
{
  return net::etech::logProcess::_descr_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::logProcess::_descr_forany>::copy (
    net::etech::logProcess::_descr_slice * _tao_to,
    const net::etech::logProcess::_descr_slice * _tao_from
  )
{
  net::etech::logProcess::_descr_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::logProcess::_descr_forany>::zero (
    net::etech::logProcess::_descr_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 250; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::logProcess::_descr_slice *
TAO::Array_Traits<net::etech::logProcess::_descr_forany>::alloc (void)
{
  return net::etech::logProcess::_descr_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_tipoProcesso_forany>::free (
    net::etech::ProcesDetail::_tipoProcesso_slice * _tao_slice
  )
{
  net::etech::ProcesDetail::_tipoProcesso_free (_tao_slice);
}

ACE_INLINE
net::etech::ProcesDetail::_tipoProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_tipoProcesso_forany>::dup (
    const net::etech::ProcesDetail::_tipoProcesso_slice * _tao_slice
  )
{
  return net::etech::ProcesDetail::_tipoProcesso_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_tipoProcesso_forany>::copy (
    net::etech::ProcesDetail::_tipoProcesso_slice * _tao_to,
    const net::etech::ProcesDetail::_tipoProcesso_slice * _tao_from
  )
{
  net::etech::ProcesDetail::_tipoProcesso_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_tipoProcesso_forany>::zero (
    net::etech::ProcesDetail::_tipoProcesso_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 10; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ProcesDetail::_tipoProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_tipoProcesso_forany>::alloc (void)
{
  return net::etech::ProcesDetail::_tipoProcesso_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_nomeProcesso_forany>::free (
    net::etech::ProcesDetail::_nomeProcesso_slice * _tao_slice
  )
{
  net::etech::ProcesDetail::_nomeProcesso_free (_tao_slice);
}

ACE_INLINE
net::etech::ProcesDetail::_nomeProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_nomeProcesso_forany>::dup (
    const net::etech::ProcesDetail::_nomeProcesso_slice * _tao_slice
  )
{
  return net::etech::ProcesDetail::_nomeProcesso_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_nomeProcesso_forany>::copy (
    net::etech::ProcesDetail::_nomeProcesso_slice * _tao_to,
    const net::etech::ProcesDetail::_nomeProcesso_slice * _tao_from
  )
{
  net::etech::ProcesDetail::_nomeProcesso_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_nomeProcesso_forany>::zero (
    net::etech::ProcesDetail::_nomeProcesso_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 100; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ProcesDetail::_nomeProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_nomeProcesso_forany>::alloc (void)
{
  return net::etech::ProcesDetail::_nomeProcesso_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKnomeProcesso_forany>::free (
    net::etech::ProcesDetail::_colorBKnomeProcesso_slice * _tao_slice
  )
{
  net::etech::ProcesDetail::_colorBKnomeProcesso_free (_tao_slice);
}

ACE_INLINE
net::etech::ProcesDetail::_colorBKnomeProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKnomeProcesso_forany>::dup (
    const net::etech::ProcesDetail::_colorBKnomeProcesso_slice * _tao_slice
  )
{
  return net::etech::ProcesDetail::_colorBKnomeProcesso_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKnomeProcesso_forany>::copy (
    net::etech::ProcesDetail::_colorBKnomeProcesso_slice * _tao_to,
    const net::etech::ProcesDetail::_colorBKnomeProcesso_slice * _tao_from
  )
{
  net::etech::ProcesDetail::_colorBKnomeProcesso_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKnomeProcesso_forany>::zero (
    net::etech::ProcesDetail::_colorBKnomeProcesso_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 10; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ProcesDetail::_colorBKnomeProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKnomeProcesso_forany>::alloc (void)
{
  return net::etech::ProcesDetail::_colorBKnomeProcesso_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_msgProcesso_forany>::free (
    net::etech::ProcesDetail::_msgProcesso_slice * _tao_slice
  )
{
  net::etech::ProcesDetail::_msgProcesso_free (_tao_slice);
}

ACE_INLINE
net::etech::ProcesDetail::_msgProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_msgProcesso_forany>::dup (
    const net::etech::ProcesDetail::_msgProcesso_slice * _tao_slice
  )
{
  return net::etech::ProcesDetail::_msgProcesso_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_msgProcesso_forany>::copy (
    net::etech::ProcesDetail::_msgProcesso_slice * _tao_to,
    const net::etech::ProcesDetail::_msgProcesso_slice * _tao_from
  )
{
  net::etech::ProcesDetail::_msgProcesso_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_msgProcesso_forany>::zero (
    net::etech::ProcesDetail::_msgProcesso_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 150; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ProcesDetail::_msgProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_msgProcesso_forany>::alloc (void)
{
  return net::etech::ProcesDetail::_msgProcesso_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKmsgProcesso_forany>::free (
    net::etech::ProcesDetail::_colorBKmsgProcesso_slice * _tao_slice
  )
{
  net::etech::ProcesDetail::_colorBKmsgProcesso_free (_tao_slice);
}

ACE_INLINE
net::etech::ProcesDetail::_colorBKmsgProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKmsgProcesso_forany>::dup (
    const net::etech::ProcesDetail::_colorBKmsgProcesso_slice * _tao_slice
  )
{
  return net::etech::ProcesDetail::_colorBKmsgProcesso_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKmsgProcesso_forany>::copy (
    net::etech::ProcesDetail::_colorBKmsgProcesso_slice * _tao_to,
    const net::etech::ProcesDetail::_colorBKmsgProcesso_slice * _tao_from
  )
{
  net::etech::ProcesDetail::_colorBKmsgProcesso_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKmsgProcesso_forany>::zero (
    net::etech::ProcesDetail::_colorBKmsgProcesso_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 10; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ProcesDetail::_colorBKmsgProcesso_slice *
TAO::Array_Traits<net::etech::ProcesDetail::_colorBKmsgProcesso_forany>::alloc (void)
{
  return net::etech::ProcesDetail::_colorBKmsgProcesso_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_legami_forany>::free (
    net::etech::CS_INFO_PROC::_legami_slice * _tao_slice
  )
{
  net::etech::CS_INFO_PROC::_legami_free (_tao_slice);
}

ACE_INLINE
net::etech::CS_INFO_PROC::_legami_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_legami_forany>::dup (
    const net::etech::CS_INFO_PROC::_legami_slice * _tao_slice
  )
{
  return net::etech::CS_INFO_PROC::_legami_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_legami_forany>::copy (
    net::etech::CS_INFO_PROC::_legami_slice * _tao_to,
    const net::etech::CS_INFO_PROC::_legami_slice * _tao_from
  )
{
  net::etech::CS_INFO_PROC::_legami_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_legami_forany>::zero (
    net::etech::CS_INFO_PROC::_legami_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 10; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Long ();
    }
}

ACE_INLINE
net::etech::CS_INFO_PROC::_legami_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_legami_forany>::alloc (void)
{
  return net::etech::CS_INFO_PROC::_legami_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_proc_forany>::free (
    net::etech::CS_INFO_PROC::_nome_proc_slice * _tao_slice
  )
{
  net::etech::CS_INFO_PROC::_nome_proc_free (_tao_slice);
}

ACE_INLINE
net::etech::CS_INFO_PROC::_nome_proc_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_proc_forany>::dup (
    const net::etech::CS_INFO_PROC::_nome_proc_slice * _tao_slice
  )
{
  return net::etech::CS_INFO_PROC::_nome_proc_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_proc_forany>::copy (
    net::etech::CS_INFO_PROC::_nome_proc_slice * _tao_to,
    const net::etech::CS_INFO_PROC::_nome_proc_slice * _tao_from
  )
{
  net::etech::CS_INFO_PROC::_nome_proc_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_proc_forany>::zero (
    net::etech::CS_INFO_PROC::_nome_proc_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 11; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::CS_INFO_PROC::_nome_proc_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_proc_forany>::alloc (void)
{
  return net::etech::CS_INFO_PROC::_nome_proc_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_cmd_start_forany>::free (
    net::etech::CS_INFO_PROC::_cmd_start_slice * _tao_slice
  )
{
  net::etech::CS_INFO_PROC::_cmd_start_free (_tao_slice);
}

ACE_INLINE
net::etech::CS_INFO_PROC::_cmd_start_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_cmd_start_forany>::dup (
    const net::etech::CS_INFO_PROC::_cmd_start_slice * _tao_slice
  )
{
  return net::etech::CS_INFO_PROC::_cmd_start_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_cmd_start_forany>::copy (
    net::etech::CS_INFO_PROC::_cmd_start_slice * _tao_to,
    const net::etech::CS_INFO_PROC::_cmd_start_slice * _tao_from
  )
{
  net::etech::CS_INFO_PROC::_cmd_start_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_cmd_start_forany>::zero (
    net::etech::CS_INFO_PROC::_cmd_start_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 50; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::CS_INFO_PROC::_cmd_start_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_cmd_start_forany>::alloc (void)
{
  return net::etech::CS_INFO_PROC::_cmd_start_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_fep_forany>::free (
    net::etech::CS_INFO_PROC::_nome_fep_slice * _tao_slice
  )
{
  net::etech::CS_INFO_PROC::_nome_fep_free (_tao_slice);
}

ACE_INLINE
net::etech::CS_INFO_PROC::_nome_fep_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_fep_forany>::dup (
    const net::etech::CS_INFO_PROC::_nome_fep_slice * _tao_slice
  )
{
  return net::etech::CS_INFO_PROC::_nome_fep_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_fep_forany>::copy (
    net::etech::CS_INFO_PROC::_nome_fep_slice * _tao_to,
    const net::etech::CS_INFO_PROC::_nome_fep_slice * _tao_from
  )
{
  net::etech::CS_INFO_PROC::_nome_fep_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_fep_forany>::zero (
    net::etech::CS_INFO_PROC::_nome_fep_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 10; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::CS_INFO_PROC::_nome_fep_slice *
TAO::Array_Traits<net::etech::CS_INFO_PROC::_nome_fep_forany>::alloc (void)
{
  return net::etech::CS_INFO_PROC::_nome_fep_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_msg_forany>::free (
    net::etech::CS_BLOCK_LOG::_msg_slice * _tao_slice
  )
{
  net::etech::CS_BLOCK_LOG::_msg_free (_tao_slice);
}

ACE_INLINE
net::etech::CS_BLOCK_LOG::_msg_slice *
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_msg_forany>::dup (
    const net::etech::CS_BLOCK_LOG::_msg_slice * _tao_slice
  )
{
  return net::etech::CS_BLOCK_LOG::_msg_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_msg_forany>::copy (
    net::etech::CS_BLOCK_LOG::_msg_slice * _tao_to,
    const net::etech::CS_BLOCK_LOG::_msg_slice * _tao_from
  )
{
  net::etech::CS_BLOCK_LOG::_msg_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_msg_forany>::zero (
    net::etech::CS_BLOCK_LOG::_msg_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 132; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::CS_BLOCK_LOG::_msg_slice *
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_msg_forany>::alloc (void)
{
  return net::etech::CS_BLOCK_LOG::_msg_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_nome_forany>::free (
    net::etech::CS_BLOCK_LOG::_nome_slice * _tao_slice
  )
{
  net::etech::CS_BLOCK_LOG::_nome_free (_tao_slice);
}

ACE_INLINE
net::etech::CS_BLOCK_LOG::_nome_slice *
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_nome_forany>::dup (
    const net::etech::CS_BLOCK_LOG::_nome_slice * _tao_slice
  )
{
  return net::etech::CS_BLOCK_LOG::_nome_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_nome_forany>::copy (
    net::etech::CS_BLOCK_LOG::_nome_slice * _tao_to,
    const net::etech::CS_BLOCK_LOG::_nome_slice * _tao_from
  )
{
  net::etech::CS_BLOCK_LOG::_nome_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_nome_forany>::zero (
    net::etech::CS_BLOCK_LOG::_nome_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 40; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::CS_BLOCK_LOG::_nome_slice *
TAO::Array_Traits<net::etech::CS_BLOCK_LOG::_nome_forany>::alloc (void)
{
  return net::etech::CS_BLOCK_LOG::_nome_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_user_forany>::free (
    net::etech::GARBAGE_INFO::_user_slice * _tao_slice
  )
{
  net::etech::GARBAGE_INFO::_user_free (_tao_slice);
}

ACE_INLINE
net::etech::GARBAGE_INFO::_user_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_user_forany>::dup (
    const net::etech::GARBAGE_INFO::_user_slice * _tao_slice
  )
{
  return net::etech::GARBAGE_INFO::_user_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_user_forany>::copy (
    net::etech::GARBAGE_INFO::_user_slice * _tao_to,
    const net::etech::GARBAGE_INFO::_user_slice * _tao_from
  )
{
  net::etech::GARBAGE_INFO::_user_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_user_forany>::zero (
    net::etech::GARBAGE_INFO::_user_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 10; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GARBAGE_INFO::_user_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_user_forany>::alloc (void)
{
  return net::etech::GARBAGE_INFO::_user_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_forany>::free (
    net::etech::GARBAGE_INFO::_path_slice * _tao_slice
  )
{
  net::etech::GARBAGE_INFO::_path_free (_tao_slice);
}

ACE_INLINE
net::etech::GARBAGE_INFO::_path_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_forany>::dup (
    const net::etech::GARBAGE_INFO::_path_slice * _tao_slice
  )
{
  return net::etech::GARBAGE_INFO::_path_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_forany>::copy (
    net::etech::GARBAGE_INFO::_path_slice * _tao_to,
    const net::etech::GARBAGE_INFO::_path_slice * _tao_from
  )
{
  net::etech::GARBAGE_INFO::_path_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_forany>::zero (
    net::etech::GARBAGE_INFO::_path_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 257; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GARBAGE_INFO::_path_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_forany>::alloc (void)
{
  return net::etech::GARBAGE_INFO::_path_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_filter_forany>::free (
    net::etech::GARBAGE_INFO::_filter_slice * _tao_slice
  )
{
  net::etech::GARBAGE_INFO::_filter_free (_tao_slice);
}

ACE_INLINE
net::etech::GARBAGE_INFO::_filter_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_filter_forany>::dup (
    const net::etech::GARBAGE_INFO::_filter_slice * _tao_slice
  )
{
  return net::etech::GARBAGE_INFO::_filter_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_filter_forany>::copy (
    net::etech::GARBAGE_INFO::_filter_slice * _tao_to,
    const net::etech::GARBAGE_INFO::_filter_slice * _tao_from
  )
{
  net::etech::GARBAGE_INFO::_filter_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_filter_forany>::zero (
    net::etech::GARBAGE_INFO::_filter_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 15; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GARBAGE_INFO::_filter_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_filter_forany>::alloc (void)
{
  return net::etech::GARBAGE_INFO::_filter_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_frequence_forany>::free (
    net::etech::GARBAGE_INFO::_frequence_slice * _tao_slice
  )
{
  net::etech::GARBAGE_INFO::_frequence_free (_tao_slice);
}

ACE_INLINE
net::etech::GARBAGE_INFO::_frequence_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_frequence_forany>::dup (
    const net::etech::GARBAGE_INFO::_frequence_slice * _tao_slice
  )
{
  return net::etech::GARBAGE_INFO::_frequence_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_frequence_forany>::copy (
    net::etech::GARBAGE_INFO::_frequence_slice * _tao_to,
    const net::etech::GARBAGE_INFO::_frequence_slice * _tao_from
  )
{
  net::etech::GARBAGE_INFO::_frequence_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_frequence_forany>::zero (
    net::etech::GARBAGE_INFO::_frequence_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 5; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GARBAGE_INFO::_frequence_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_frequence_forany>::alloc (void)
{
  return net::etech::GARBAGE_INFO::_frequence_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_log_forany>::free (
    net::etech::GARBAGE_INFO::_path_log_slice * _tao_slice
  )
{
  net::etech::GARBAGE_INFO::_path_log_free (_tao_slice);
}

ACE_INLINE
net::etech::GARBAGE_INFO::_path_log_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_log_forany>::dup (
    const net::etech::GARBAGE_INFO::_path_log_slice * _tao_slice
  )
{
  return net::etech::GARBAGE_INFO::_path_log_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_log_forany>::copy (
    net::etech::GARBAGE_INFO::_path_log_slice * _tao_to,
    const net::etech::GARBAGE_INFO::_path_log_slice * _tao_from
  )
{
  net::etech::GARBAGE_INFO::_path_log_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_log_forany>::zero (
    net::etech::GARBAGE_INFO::_path_log_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 257; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GARBAGE_INFO::_path_log_slice *
TAO::Array_Traits<net::etech::GARBAGE_INFO::_path_log_forany>::alloc (void)
{
  return net::etech::GARBAGE_INFO::_path_log_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_CAT::_name_forany>::free (
    net::etech::ACQ_CAT::_name_slice * _tao_slice
  )
{
  net::etech::ACQ_CAT::_name_free (_tao_slice);
}

ACE_INLINE
net::etech::ACQ_CAT::_name_slice *
TAO::Array_Traits<net::etech::ACQ_CAT::_name_forany>::dup (
    const net::etech::ACQ_CAT::_name_slice * _tao_slice
  )
{
  return net::etech::ACQ_CAT::_name_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_CAT::_name_forany>::copy (
    net::etech::ACQ_CAT::_name_slice * _tao_to,
    const net::etech::ACQ_CAT::_name_slice * _tao_from
  )
{
  net::etech::ACQ_CAT::_name_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_CAT::_name_forany>::zero (
    net::etech::ACQ_CAT::_name_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 29; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ACQ_CAT::_name_slice *
TAO::Array_Traits<net::etech::ACQ_CAT::_name_forany>::alloc (void)
{
  return net::etech::ACQ_CAT::_name_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_processing_forany>::free (
    net::etech::ACQ_PEB_MGR::_processing_slice * _tao_slice
  )
{
  net::etech::ACQ_PEB_MGR::_processing_free (_tao_slice);
}

ACE_INLINE
net::etech::ACQ_PEB_MGR::_processing_slice *
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_processing_forany>::dup (
    const net::etech::ACQ_PEB_MGR::_processing_slice * _tao_slice
  )
{
  return net::etech::ACQ_PEB_MGR::_processing_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_processing_forany>::copy (
    net::etech::ACQ_PEB_MGR::_processing_slice * _tao_to,
    const net::etech::ACQ_PEB_MGR::_processing_slice * _tao_from
  )
{
  net::etech::ACQ_PEB_MGR::_processing_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_processing_forany>::zero (
    net::etech::ACQ_PEB_MGR::_processing_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 29; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ACQ_PEB_MGR::_processing_slice *
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_processing_forany>::alloc (void)
{
  return net::etech::ACQ_PEB_MGR::_processing_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_PEBName_forany>::free (
    net::etech::ACQ_PEB_MGR::_PEBName_slice * _tao_slice
  )
{
  net::etech::ACQ_PEB_MGR::_PEBName_free (_tao_slice);
}

ACE_INLINE
net::etech::ACQ_PEB_MGR::_PEBName_slice *
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_PEBName_forany>::dup (
    const net::etech::ACQ_PEB_MGR::_PEBName_slice * _tao_slice
  )
{
  return net::etech::ACQ_PEB_MGR::_PEBName_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_PEBName_forany>::copy (
    net::etech::ACQ_PEB_MGR::_PEBName_slice * _tao_to,
    const net::etech::ACQ_PEB_MGR::_PEBName_slice * _tao_from
  )
{
  net::etech::ACQ_PEB_MGR::_PEBName_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_PEBName_forany>::zero (
    net::etech::ACQ_PEB_MGR::_PEBName_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 128; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::ACQ_PEB_MGR::_PEBName_slice *
TAO::Array_Traits<net::etech::ACQ_PEB_MGR::_PEBName_forany>::alloc (void)
{
  return net::etech::ACQ_PEB_MGR::_PEBName_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_nomeNodo_forany>::free (
    net::etech::GESTIONE_PSWD::_nomeNodo_slice * _tao_slice
  )
{
  net::etech::GESTIONE_PSWD::_nomeNodo_free (_tao_slice);
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_nomeNodo_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_nomeNodo_forany>::dup (
    const net::etech::GESTIONE_PSWD::_nomeNodo_slice * _tao_slice
  )
{
  return net::etech::GESTIONE_PSWD::_nomeNodo_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_nomeNodo_forany>::copy (
    net::etech::GESTIONE_PSWD::_nomeNodo_slice * _tao_to,
    const net::etech::GESTIONE_PSWD::_nomeNodo_slice * _tao_from
  )
{
  net::etech::GESTIONE_PSWD::_nomeNodo_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_nomeNodo_forany>::zero (
    net::etech::GESTIONE_PSWD::_nomeNodo_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 50; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_nomeNodo_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_nomeNodo_forany>::alloc (void)
{
  return net::etech::GESTIONE_PSWD::_nomeNodo_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_oggetto_forany>::free (
    net::etech::GESTIONE_PSWD::_oggetto_slice * _tao_slice
  )
{
  net::etech::GESTIONE_PSWD::_oggetto_free (_tao_slice);
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_oggetto_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_oggetto_forany>::dup (
    const net::etech::GESTIONE_PSWD::_oggetto_slice * _tao_slice
  )
{
  return net::etech::GESTIONE_PSWD::_oggetto_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_oggetto_forany>::copy (
    net::etech::GESTIONE_PSWD::_oggetto_slice * _tao_to,
    const net::etech::GESTIONE_PSWD::_oggetto_slice * _tao_from
  )
{
  net::etech::GESTIONE_PSWD::_oggetto_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_oggetto_forany>::zero (
    net::etech::GESTIONE_PSWD::_oggetto_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 100; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_oggetto_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_oggetto_forany>::alloc (void)
{
  return net::etech::GESTIONE_PSWD::_oggetto_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_login_forany>::free (
    net::etech::GESTIONE_PSWD::_login_slice * _tao_slice
  )
{
  net::etech::GESTIONE_PSWD::_login_free (_tao_slice);
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_login_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_login_forany>::dup (
    const net::etech::GESTIONE_PSWD::_login_slice * _tao_slice
  )
{
  return net::etech::GESTIONE_PSWD::_login_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_login_forany>::copy (
    net::etech::GESTIONE_PSWD::_login_slice * _tao_to,
    const net::etech::GESTIONE_PSWD::_login_slice * _tao_from
  )
{
  net::etech::GESTIONE_PSWD::_login_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_login_forany>::zero (
    net::etech::GESTIONE_PSWD::_login_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 50; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_login_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_login_forany>::alloc (void)
{
  return net::etech::GESTIONE_PSWD::_login_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_pswd_forany>::free (
    net::etech::GESTIONE_PSWD::_pswd_slice * _tao_slice
  )
{
  net::etech::GESTIONE_PSWD::_pswd_free (_tao_slice);
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_pswd_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_pswd_forany>::dup (
    const net::etech::GESTIONE_PSWD::_pswd_slice * _tao_slice
  )
{
  return net::etech::GESTIONE_PSWD::_pswd_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_pswd_forany>::copy (
    net::etech::GESTIONE_PSWD::_pswd_slice * _tao_to,
    const net::etech::GESTIONE_PSWD::_pswd_slice * _tao_from
  )
{
  net::etech::GESTIONE_PSWD::_pswd_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_pswd_forany>::zero (
    net::etech::GESTIONE_PSWD::_pswd_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 50; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_pswd_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_pswd_forany>::alloc (void)
{
  return net::etech::GESTIONE_PSWD::_pswd_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_array/array_ci.cpp:150

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_dataScadenza_forany>::free (
    net::etech::GESTIONE_PSWD::_dataScadenza_slice * _tao_slice
  )
{
  net::etech::GESTIONE_PSWD::_dataScadenza_free (_tao_slice);
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_dataScadenza_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_dataScadenza_forany>::dup (
    const net::etech::GESTIONE_PSWD::_dataScadenza_slice * _tao_slice
  )
{
  return net::etech::GESTIONE_PSWD::_dataScadenza_dup (_tao_slice);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_dataScadenza_forany>::copy (
    net::etech::GESTIONE_PSWD::_dataScadenza_slice * _tao_to,
    const net::etech::GESTIONE_PSWD::_dataScadenza_slice * _tao_from
  )
{
  net::etech::GESTIONE_PSWD::_dataScadenza_copy (_tao_to, _tao_from);
}

ACE_INLINE
void
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_dataScadenza_forany>::zero (
    net::etech::GESTIONE_PSWD::_dataScadenza_slice * _tao_slice
  )
{
  // Zero each individual element.
  for ( ::CORBA::ULong i0 = 0; i0 < 9; ++i0)
    {
      _tao_slice[i0] = ::CORBA::Char ();
    }
}

ACE_INLINE
net::etech::GESTIONE_PSWD::_dataScadenza_slice *
TAO::Array_Traits<net::etech::GESTIONE_PSWD::_dataScadenza_forany>::alloc (void)
{
  return net::etech::GESTIONE_PSWD::_dataScadenza_alloc ();
}


// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::ConfigMonitorServer::ConfigMonitorServer (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::ConfigMonitorServer::ConfigMonitorServer (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::ProcessMonitorServer::ProcessMonitorServer (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::ProcessMonitorServer::ProcessMonitorServer (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::ACQPebMGRServer::ACQPebMGRServer (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::ACQPebMGRServer::ACQPebMGRServer (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::GarbageMonitorServer::GarbageMonitorServer (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::GarbageMonitorServer::GarbageMonitorServer (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::PsMonitorMasterServer::PsMonitorMasterServer (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::PsMonitorMasterServer::PsMonitorMasterServer (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::DBConfigurationServer::DBConfigurationServer (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::DBConfigurationServer::DBConfigurationServer (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::ssm_scheduler_server::ssm_scheduler_server (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::ssm_scheduler_server::ssm_scheduler_server (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::ssm_logger_server::ssm_logger_server (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::ssm_logger_server::ssm_logger_server (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

// TAO_IDL - Generated from
// be/be_visitor_interface/interface_ci.cpp:62

ACE_INLINE
net::etech::SSM::ssm_garbage_server::ssm_garbage_server (
    TAO_Stub *objref,
    ::CORBA::Boolean _tao_collocated,
    TAO_Abstract_ServantBase *servant,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (objref, _tao_collocated, servant, oc)
{
}

ACE_INLINE
net::etech::SSM::ssm_garbage_server::ssm_garbage_server (
    ::IOP::IOR *ior,
    TAO_ORB_Core *oc)
  : ::CORBA::Object (ior, oc)
{
}

