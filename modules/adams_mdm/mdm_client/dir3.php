<?php

if ( isset($_GET['root_queue']) && $_GET['root_queue'] != '' )
    $root = $_GET['root_queue'];

$urlBase = "http://t_rda/NTM_Reports/";

function callbackDir($dir)
{ 
   $arrPath = explode ("/",$dir);
   $indLast = count($arrPath) - 1;
   $nameDir = $arrPath[$indLast];
   
   echo "<directory name=\"$nameDir\">\n";
}

function closecallbackDir()
{
    echo "</directory>\n";
}

function callbackFile($urlBase, $file)
{ 
   $arrPath = explode ("/",$file);
   $indLast = count($arrPath) - 1;
   $nameFile = $arrPath[$indLast];
   $urlFile = $urlBase . $file;
   $dataFile = date("Y m d H:i:s",filemtime($file));
   echo "<file data=\"$dataFile\" name=\"$nameFile\">$urlFile</file>\n";
}


function walkDir($dir)
{
    $arDirStack = array();
    
    if( ($dh=opendir($dir)) )
    { 
        while( ($file=readdir($dh))!== false )
        {
            if( $file=='.' || $file=='..' || $file=='index.html' ) 
                continue;
                
            if( is_dir("$dir/$file") )
            { 
                $subdir = "$dir/$file";
                $arSubDir = walkDir($subdir);
                $arDirStack[] = array("name"=>$subdir,"value"=>$arSubDir);
            } else
            {
                list($corpo,$estenzione) = explode(".",$file);
                if ($estenzione == "html")
                {
                    $arDirStack[] = "$dir/$file";
                }
            }
            
        }
        closedir($dh);
    }
    
    return $arDirStack;
}


function showAlbero($urlBase, $nome, $arrAlbero)
{
    callbackDir($nome);
    
    foreach( $arrAlbero as $subdir )
    {
        if (is_array($subdir))
        {
            //echo $subdir["name"] . "<br>";
            showAlbero($urlBase, $subdir["name"], $subdir["value"]);
        } else
        {
            callbackFile($urlBase, $subdir);
            //echo $subdir . "<br>";
        }
    }
    
    closecallbackDir();
}

if ( isset($_GET['dir']) && $_GET['dir'] != '' )
    $subdir = $_GET['dir'];

    
    
if ( isset($subdir) )
{
    $dirRoot = $root."/".$subdir;
}
else
{
    $dirRoot = $root;
}

$arrAlbero = walkDir($dirRoot);
showAlbero($urlBase, $dirRoot, $arrAlbero);


?> 
