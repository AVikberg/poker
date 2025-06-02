import { revalidatePath } from "next/cache";
import { PokerAnalysis } from "./ui/components/PokerHand/types";

export const BASE_URL = "http://server:8080";

export async function CreateNewHand() {
  'use server';
  const data = await fetch(`${BASE_URL}/hands`, {method: "POST"});
  const newHand: PokerAnalysis = await data.json();
  console.log(newHand);
  revalidatePath('/poker');
}
