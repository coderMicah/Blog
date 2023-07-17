import { cache } from "react"
import { createClient } from 'next-sanity'


const apiVersion = process.env.NEXT_PUBLIC_SANITY_API_VERSION || "2023-07-17";
const dataset = process.env.NEXT_PUBLIC_SANITY_DATASET;
const projectId = process.env.NEXT_PUBLIC_SANITY_PROJECT_ID;
const useCdn = false;

export const client = createClient({
  apiVersion,
  dataset,
  projectId,
  useCdn,
})

export const cachedClient = cache(client.fetch.bind(client))
