import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmotionalMapsVers2 {
	
	static TreeMapEventi TreeEventi;
	static List<int[][]> map;
	
	  public static void main(String[] args) throws IOException
	  {
		  TreeEventi=new TreeMapEventi();
		  map=new ArrayList<int[][]>();
		  String comandipath="";
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  File file;
		  
		  System.out.println("Specifica il percorso del file comandi da inserire!\n");
		  try
		  {

			  comandipath=br.readLine();
			  if (comandipath == "")
				  System.out.println("Inserire un percorso valido per la lettura del file!\n");

			  file = new File(comandipath);
			  br = new BufferedReader(new FileReader(file));
			  String tmp="";
			  
                do
				{
				  tmp=br.readLine();
				  String eventoargomento=tmp.substring(tmp.indexOf('(')+1, tmp.indexOf(')'));
				  if(tmp.matches("import(.+)"))
				  {
					  comandipath=comandipath.replace("Comandi.txt", "");
					  Operazioni.Import(comandipath+eventoargomento);
				  }
				  else
				  {
					  if(tmp.matches("create_map(.+)")&& eventoargomento.contains("-"))
					  {
						  String[] intervallo=eventoargomento.split("-");
						  map=Operazioni.CreateMap(intervallo[0],intervallo[1]);
						  StampaMap();
					  }
					  else
						  throw new ComandiException("Il comando inserito non è corretto!\n");
				  }
				}while (br.ready());
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
		  String tmp="";
		  int indexMap=1;
		  int NumPOI=PuntoDiInteresse.values().length,NumStatiEmozione=StatoEmozione.values().length;	
		  for(int[][] Arraymap : map)
		  {
			  switch(indexMap)
			  {
			  case 1:
				  tmp="\nUtenti attivi: \n";
				  break;
			  case 2:
				  tmp="\nTotale utenti: \n";
				  break;
			   default:
				  break;
			  }
			  
			  for (int i=0;i<NumPOI;i++)
			  {
				  tmp=tmp+"POI"+(i+1)+" - ";
					for(int j=0;j<NumStatiEmozione;j++)
					{
						tmp=tmp+Arraymap[i][j]+"% "+StatoEmozione.values()[j].GetEmozione()+", ";
					}
				  tmp=tmp.substring(0,tmp.lastIndexOf(','));
				  tmp=tmp+"\n";
			  }
		
			  System.out.println(tmp);
			  indexMap++;
		  }
	  }
}
