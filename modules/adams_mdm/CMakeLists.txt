

cmake_minimum_required(VERSION 2.8)

project (adams_mdm)

add_subdirectory(mdm_server_factory)
add_subdirectory(mdm_agent_factory)
add_subdirectory(mdm_server)
add_subdirectory(mdm_agent)
add_subdirectory(mdm_client)
add_subdirectory(mdm_job_scheduler)
add_subdirectory(mdm_server_job)

