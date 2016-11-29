

cmake_minimum_required(VERSION 2.8)

project (adams_ssm)

add_subdirectory(ssm_logger)
add_subdirectory(ssm_scheduler)
add_subdirectory(ssm_garbage)
add_subdirectory(ssm_deployer)
add_subdirectory(ssm_utils)
add_subdirectory(ssm_client)

