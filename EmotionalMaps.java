package soluzione;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmotionalMaps {
	
	static TreeMapEventi treeEventi;
	static List<int[][]> emotionalMap;
	
	  public static void main(String[] args) throws IOException
	  {
		  treeEventi=new TreeMapEventi();
		  emotionalMap=new ArrayList<int[][]>();
		  String path="";
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//qui lo uso per leggere il percorso
		  File file;
		  
		  System.out.println("Specifica il percorso del file comandi da inserire!\n");
		  try
		  {

			  path=br.readLine();
			  if (path == "")
				  System.out.println("Inserire un percorso valido per la lettura del file!\n");

			  file = new File(path);//qui il file
			  br = new BufferedReader(new FileReader(file));
			  String comando="";
			  
			  while (br.ready())
				{
				  comando=br.readLine();
				  String comandoArgomento=comando.substring(comando.indexOf('(')+1, comando.indexOf(')'));
				  if(comando.matches("import(.+)"))
				  {
					  path=path.replace("Comandi.txt", "");//per togliere comandi.txt e far rimanere solo il percorso
					  Operazioni.Import(path+comandoArgomento);
				  }
				  else
				  {
					  if(comando.matches("create_map(.+)") && comandoArgomento.contains("-"))
					  {
						  String[] intervalloData=comandoArgomento.split("-");
						  emotionalMap=Operazioni.CreateMap(intervalloData[0],intervalloData[1]);
						  StampaMap();
					  }
					  else
						  throw new ComandiException("Il comando inserito non è corretto!\n");
				  }
				} 
		  }
		  catch (Throwable e)
		  {
			  System.out.println("Errore:"+e.getMessage()+"\n");
		  }
		  finally
		  {
			  br.close();
		  }
	
	  }
	  
	  static private void StampaMap ()
	  {
		  String tmpRigaMappa="";
		  int indexMap=1;//per far si che tutti i titoli delle mappe vengano visualizzate
		  int NumPOI=PuntoDiInteresse.values().length,NumStatiEmozione=StatoEmozione.values().length;	
		  for(int[][] Arraymap : emotionalMap)
		  {
			  switch(indexMap)
			  {
			  case 1:
				  tmpRigaMappa="\nUtenti attivi: \n";
				  break;
			  case 2:
				  tmpRigaMappa="\nTotale utenti: \n";
				  break;
			   default:
				  break;
			  }
			  
			  for (int i=0;i<NumPOI;i++)
			  {
				  tmpRigaMappa=tmpRigaMappa+"POI"+(i+1)+" - ";
					for(int j=0;j<NumStatiEmozione;j++)
					{
						tmpRigaMappa=tmpRigaMappa+Arraymap[i][j]+"% "+StatoEmozione.values()[j].GetEmozione()+", ";
					}
				  tmpRigaMappa=tmpRigaMappa.substring(0,tmpRigaMappa.lastIndexOf(','));
				  tmpRigaMappa=tmpRigaMappa+"\n";
			  }
		
			  System.out.println(tmpRigaMappa);
			  indexMap++;
		  }
	  }
}
