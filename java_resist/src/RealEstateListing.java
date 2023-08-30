import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RealEstateListing {
	public static void main(String[] args) {
		HashMap<String, String[]> HomelyListings = new HashMap<>();
		//open file and read
		String fileName = "Listings.txt";
		String delimiter = ",";
		String[] array;

		try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bReader.readLine()) != null) {
				array = line.split(delimiter);
				if (array.length == 17) { // Ensure the correct number of fields are present
					String key = array[0];
					HomelyListings.put(key, array);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		//start the program
		System.out.print("Welcome to Homely Inc. real-estate service. Here, you can enquire about the real estates that are available either for buying or renting.");
		System.out.println("We offer both residential and commercial spaces.\n");
		MAIN:
		while (true) {
			System.out.println("Are you interested in buying or renting? Enter either 1, 2, or 3:");
			System.out.println("1. Buy");
			System.out.println("2. Rent");
			System.out.println("3. Both");
			System.out.print("Your input: ");
			int buyOrRentChoice = scanner.nextInt();

			System.out.println("\nSelect the real-estate type you would like to enquire about. Enter either 1 or 2:");
			System.out.println("1. Residence");
			System.out.println("2. Commercial");
			System.out.print("Your input: ");
			int residenceOrCommercialChoice = scanner.nextInt();

			System.out.println("\nSelect the surface area of the place you're interested in. Enter either 1, 2, 3, or 4:");
			System.out.println("1. Small (under 30sq.m.)");
			System.out.println("2. Medium (>30 sq.m. and <= 60 sq.m.)");
			System.out.println("3. Large (>60 sq.m. and <= 150 sq.m.)");
			System.out.println("4. Very large (>150 sq.m.)");
			System.out.print("Your input: ");
			int areaChoice = scanner.nextInt();

		FIRST:
			while(true){
				//filter matching listings
				int numOfMatching=1;
				String[] detailsOfKey=new String[20];
				System.out.println("\nMatching your inputs, following are the places we found for you:");
				for (String key : HomelyListings.keySet()) {
					//check
					String[] keyParts = key.split("");

					char BRchoice = 'A';
					if (buyOrRentChoice == 1) {
						BRchoice = 'B';
					} else if (buyOrRentChoice == 2) {
						BRchoice = 'R';
					}
					if(BRchoice!='A'){
						if(BRchoice!=keyParts[8].charAt(0)){
							continue;
						}
					}

					char RCchoice =0;
					if (residenceOrCommercialChoice == 1) {
						RCchoice = 'R';//Integer.parseInt(keyParts[8]);
					} else if (residenceOrCommercialChoice == 2) {
						RCchoice = 'C';//Integer.parseInt(keyParts[8]);
					}
					if(RCchoice!=keyParts[0].charAt(0)){
						continue;
					}

					String areaKind = null;
					if (areaChoice == 1) {
						areaKind = "SML";
					} else if (areaChoice == 2) {
						areaKind = "MED";
					} else if (areaChoice == 3) {
						areaKind = "LRG";
					} else if (areaChoice == 4) {
						areaKind = "VLG";
					}
					String areaParts = keyParts[5].charAt(0)+""+keyParts[6].charAt(0)+""+keyParts[7].charAt(0);
					if (!areaParts.equals(areaKind)) {
						continue;
					}
					//display
					String[] listingDetails = HomelyListings.get(key);
					System.out.print(numOfMatching+".");
					detailsOfKey[numOfMatching]=key;
					System.out.println("  City:"+listingDetails[2]);
					System.out.println("\tType:"+listingDetails[3]);
					System.out.println("\tApartment description:"+listingDetails[4]);
					System.out.println("\tArea:"+listingDetails[5]);
					System.out.println("\tAvailability:"+listingDetails[13]);
					numOfMatching++;
				}

				System.out.println("\nIf you would like to know more about any of the above listed place(s), enter the corresponding number (e.g., 1, 2, etc.).");
				System.out.println("If you want to search again, enter \"BACK\" or \"back\".");
				System.out.println("If you want to end your search query, enter \"END\" or \"end\".");
				System.out.print("Your input: ");
				String userInput = scanner.next();

				if (userInput.equalsIgnoreCase("END")) {
					System.out.print("\nThank you for choosing Homely Inc. We hope you found what you were looking for.");
					System.out.print("If not, then visit us soon, and give us another chance to meet your requirement.");
					System.out.println("You can also get in touch with us at homely.homes@homely.com.");
					break MAIN;
				} else if (userInput.equalsIgnoreCase("BACK")) {
					// Continue to the next iteration of the loop
					continue MAIN;
				} else {
					try {
						int selectedNumber = Integer.parseInt(userInput);
						String curKey= detailsOfKey[selectedNumber];
						String[] infoDetail=HomelyListings.get(curKey);
						// Display details of the selected listing
						String valueOfinfo = "Listing type: " + infoDetail[1] + "\n" +
								"Location: " + infoDetail[2] + "\n" +
								"Type: " + infoDetail[3] + "\n" +
								"Apartment Description: " + infoDetail[4] + "\n" +
								"Surface Area: " + infoDetail[5] + "\n" +
								"Floor: " + infoDetail[6] + "\n" +
								"Total Floors: " + infoDetail[7] + "\n" +
								"Construction Year: " + infoDetail[8] + "\n" +
								"Price/Rent: " + infoDetail[9] + "\n" +
								"Loan: " + infoDetail[10] + "\n" +
								"Balcony: " + infoDetail[11] + "\n" +
								"Elevator: " + infoDetail[12] + "\n" +
								"Availability: " + infoDetail[13] + "\n" +
								"Agent Name: " + infoDetail[14] + "\n" +
								"Agent Contact: " + infoDetail[15] + "\n" +
								"Agent Email: " + infoDetail[16];
						System.out.println("\n"+valueOfinfo+"\n");
						System.out.println("1. Book a showing");
						System.out.println("2. Go back to the results");
						System.out.println("3. Search again");
						System.out.println("4. End");
						System.out.print("Your input: ");
						int userInput2=scanner.nextInt();
						if(userInput2==1){
							System.out.print("Please input your fist name:");
							String firstName=scanner.next();
							System.out.print("Please input your last name:");
							String lastName=scanner.next();
							System.out.print("Please input your gender:");
							String gender=scanner.nextLine();
							gender=scanner.nextLine();
							System.out.print("Please input your date of birth:");
							String dateOfBirth=scanner.nextLine();
							System.out.print("Please input your phone number:");
							String phoneNumber=scanner.nextLine();
							System.out.print("Please input your email address:");
							String emailAddress=scanner.nextLine();
							//getDate
							Date dateOfShow=null;
							String dos = null;
							Date currentDate = new Date();
							Date startDate =new Date(currentDate.getTime()-24L * 60 * 60 * 1000);
							SimpleDateFormat ft = new SimpleDateFormat("MMMM dd", Locale.ENGLISH);
							Date maxAllowedDate = new Date(currentDate.getTime() + 30L * 24 * 60 * 60 * 1000); // 30 days later
							Date endDate=new Date(maxAllowedDate.getTime()+24L * 60 * 60 * 1000);
							boolean inputcorrect = false;
							Calendar calendar = Calendar.getInstance(); // Create a calendar instance to set the default year

							do {
								System.out.print("Please input your date to have a show (from " + ft.format(currentDate) + " to " + ft.format(maxAllowedDate) + "): ");
								dos = scanner.nextLine(); // Use nextLine() to read the whole line of input

								try {
									calendar.setTime(currentDate); // Set the calendar to the current date
									Date t = ft.parse(dos);
									calendar.set(Calendar.MONTH, t.getMonth()); // Set the month
									calendar.set(Calendar.DAY_OF_MONTH, t.getDate()); // Set the day
									dateOfShow = calendar.getTime(); // Get the resulting date
									//System.out.println(ft.format(dateOfShow));
									if(dateOfShow.after(startDate)&& dateOfShow.before(endDate)){
										inputcorrect = true; // Set inputcorrect to true to exit the loop
									}
								} catch (ParseException e) {
									System.out.println("Invalid date format. Please use format like 'Month Day' (e.g. 'August 28').");
								}
							} while (!inputcorrect);

							//getTime
							String timeOfShow=null;
							inputcorrect=false;
							String[] tmpArray;
							do{
								System.out.print("Please input the time(9:00 AM to 4:00 PM):");
								timeOfShow=scanner.nextLine();
								if(timeOfShow.length()<7||timeOfShow.length()>8){
									System.out.println("input error,please input again");
									continue;
								}else {
									tmpArray = timeOfShow.split(":");
									int timeNum=Integer.parseInt(tmpArray[0]);
									if(tmpArray[1].charAt(3)=='A'){
										if(timeNum>=9&&timeNum<=12){
											inputcorrect=true;
										}else{
											System.out.println("input error,please input again");
											continue;
										}
									}else if(tmpArray[1].charAt(3)=='P'){
										if(timeNum>=1&&timeNum<=4){
											inputcorrect=true;
										}else{
											System.out.println("input error,please input again");
											continue;
										}
									}
								}
							}while(!inputcorrect);
							System.out.println("Thank you booking a showing with Homely Inc.\n" +
									"Our agent Ms. Marge Marlow will be delighted to show you your chosen place and " +
									"answer all your queries to help you make the right decision.\n" +
									"We will also send you two reminders before the showing day to make sure you don't " +
									"miss it. \n" +
									"For any questions or cancellation of the showing, contact us at " +
									"homely.homes@homely.com");

							break MAIN;
						}else if(userInput2==2){
							continue FIRST;
						}else if(userInput2==3){
							continue MAIN;
						}else if(userInput2==4){
							break MAIN;
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a valid number or \"BACK\" or \"END\".");
					}
				}
			}
		}
	}
}
